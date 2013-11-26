/* control storage.  this one is pretty hairy. */

import java.io.*;
import java.util.*;

public class CS implements ClockedDevice
{
  private final int FETCH = 0x40;
  private final int TRANSFER = 0xe;
  private final int START_STAGE = 4;

  private IR ir; private MainMemory mem; 
  private Registers regs; private ALU alu; 
  private Shifter shifter; private SR sr;
  private Bus bus;

  private boolean W_CSAR, R_CS, R_CSAR, W_CSDR;
  private boolean START;
  private boolean INV_INS, CLOCK;
  private boolean HALT;
  private boolean BRANCH;

  private int INT_TYPE;
  private int CSAR;
  private CSWord CSDR;
  private CSWord CSDRbuf;
  private int stage;
  private int nextStage;
  private int CSReadStart;

  private static int CSIZE = 128;
  private CSWord CS[] = new CSWord[CSIZE];

  public boolean progress;

  public String toString()
    {
      return "CS status: CSAR="+Integer.toString(CSAR, 16)+
	" CSDR="+CSDR+
	" CSDRbuf="+CSDRbuf+
	" stage="+stage+
	" nextStage="+nextStage+
	" R_CSAR="+R_CSAR;
    }

  public void setW_CSAR(boolean b) { W_CSAR = b; }
  public void setR_CS(boolean b) { R_CS = b; }
  public void setR_CSAR(boolean b) { R_CSAR = b; }

  public boolean getHalt() { return HALT; }

  public CS(IR ir, MainMemory mem, Registers regs, 
	    ALU alu, Shifter shifter, SR sr, Bus bus)
    {
      this.ir = ir; this.mem = mem; this.regs = regs;
      this.alu = alu; this.shifter = shifter; this.sr = sr;
      this.bus = bus;
      START = true; HALT = false;

      for (int i = 0; i < CSIZE; i++)
	{
	  CS[i] = new CSWord();
	}

      /* load CS from disk */

      BufferedReader in = null;

      try
	{ in = new BufferedReader(new FileReader("cs")); }
      catch(FileNotFoundException e)
	{
	  System.err.println("Could not open cs!");
	  System.exit(1);
	}
      String s; int i = 0;

      try
	{
	  while ((s = in.readLine()) != null)
	    {
	      if (s.length() == 0) continue;
	      CS[i].setAddr(Integer.parseInt(s.substring(0, 2), 16));
	      for (int j = 0; j < 3; j++)
		CS[i].setOp(j, Long.parseLong
			    (s.substring(2+j*10, 12+j*10), 16));
	      i++;
	    }
	}
      catch(IOException e)
	{
	  System.err.println("Error reading from cs!");
	  System.exit(1);
	}
    }

  /* read the microcode instruction from the appropriate place */
  /* as promised in the spec. note that starge > 4 are start stages */
  /* could be shorter at the expense of clarity. */
  private long getOp()
    {
      switch(stage)
	{
	case 0:
	  return CSDR.getOp(stage);
	case 4:
	  return CSDR.getOp(stage-4);
	case 1:
	  return CSDRbuf.getOp(stage);
	case 5:
	  return CSDR.getOp(stage-4);
	case 2:
	  return CSDRbuf.getOp(stage);
	case 6:
	  return CSDR.getOp(stage-4);
	}
      return 0;
    }

  /* we consider the machine to be fetching when stage is 0 */
  /* and CSAR points to FETCH -- used to print at each instruction */
  /* also just after a TRANSFER we need to count an instruction. */
  public boolean isFetching()
    {
      return ((stage == 0) && (CSAR == FETCH)) ||
	((stage == 1) && (CSAR == 14));
    }

  /* C is true iff bb == SR -- utility routine for CONDTRA(*) */
  private boolean C()
    {
      boolean s0 = (ir.getb0b1() & 1) == 1;
      boolean s1 = (ir.getb0b1() & 2) == 2;
      return s0 == sr.getSR1() && s1 == sr.getSR2();
    }

  /* set the appropriate gates corresponding 
   * to our current microinstruction */
  /* must be executed before anything else in the cycle */
  public void ClockCycleWrite(int clock)
    {
      /* a big mess corresponding to a big mess in the circuit */
      /* it does, however, make the machine fast */
      BRANCH = (ir.getCS() == 16) || (ir.getCS() == 17);
      if (START || (stage == START_STAGE && BRANCH && !C())) /* FETCH */
	{
	  stage = START_STAGE; START = false;
	  CSAR = FETCH;
	}
      else if (stage == START_STAGE) /* RESET & !START => read IR */
	{
	  if (BRANCH && ((ir.getCS() & 1) == 0)) /* CONDTRA */
	    {
	      CSAR = C() ? 14 : FETCH;
	    }
	  else if (BRANCH && ((ir.getCS() & 1) == 1)) /* CONDTRA* */
	    {
	      CSAR = C() ? 15 : FETCH;
	    }
	  else setR_CSAR(true);
	}
      else
	setR_CSAR(false);

      progress = false;
      /* if we're up to run another microinstruction... */
      if (clock == nextStage)
	{
	  progress = true;

	  stage++;

	  /* simulate the stage-resetting and load CSDR from CSAR */
	  if (stage == 3 || stage == 7)
	    {
	      stage = 0;
	      CSDR = CS[CSAR];
	    }
	  
	  /* at stage 1 we load CSDRbuf so we can run from buf */
	  /* we also load CSAR from CSAR */
	  if (stage == 1)
	    {
	      CSDRbuf = CSDR;
	      CSAR = CSDR.getAddr();
	    }

	  /* for RESET stages we run for 20ns regardless. */
	  if (stage >= START_STAGE)
	    {
	      nextStage = clock + 20;
	      return;
	    }
	  else
	    {
	      /* fetch the timing for the next microinstruction */
	      long l = getOp();
	      int timing = (((int)(l & (7 << 4)) >> 4)-1) * 20;
	      if (timing == 0)
		{ 
		  stage = START_STAGE; 
		  nextStage = clock + 20; 
		  return; 
		}
	      nextStage = clock + timing;
	    }
	}

      /* process the current instruction */
      long l = getOp();
     
      HALT = (l & (1 << 0)) != 0;
      if ((l & (1 << 1)) != 0)
	{
	  INV_INS = true;
	  INT_TYPE = 4; 
	}
      if ((l & (1 << 2)) != 0)
	bus.setValue(INT_TYPE);
      if ((l & (1 << 3)) != 0) bus.setHigh(0);
      /* ignore microcode instruction length here */
      ir.setW_IR((l & (1 << 7)) != 0);
      sr.setSHFALU((l & (1 << 8)) != 0);
      sr.setW_SR12((l & (1 << 9)) != 0);
      if ((l & (1 << 11)) != 0)
	{
	  sr.setSR0((l & (1 << 10)) != 0);
	  if ((l & (1 << 10)) == 0) /* we just set SR[0] to 0 */
	    {
	      INV_INS = false;      /* as promised, clear ff's */
	      CLOCK = false;
	    }
	}

      shifter.setR_ISHFT((l & (1 << 12)) != 0);
      shifter.setW_ISHFT((l & (1 << 13)) != 0);

      alu.setR_ALU((l & (1 << 14)) != 0);
      alu.setMC_op((int)(l & (7 << 15))>>15);
      alu.setIRALU((l & (1 << 18)) != 0);
      alu.setALU_PLUS2((l & (1 << 19)) != 0);
      alu.setW_RALU((l & (1 << 20)) != 0);
      alu.setW_LALU((l & (1 << 21)) != 0);

      mem.setW_MSDR((l & (1 << 22)) != 0);
      mem.setR_MSDR((l & (1 << 23)) != 0);
      mem.setSDmode((l & (1 << 24)) != 0);
      mem.setW_MEM((l & (1 << 25)) != 0);
      mem.setR_MEM((l & (1 << 26)) != 0);
      mem.setW_MSAR((l & (1 << 27)) != 0);
      mem.setR_MSAR((l & (1 << 28)) != 0);
      
      regs.setW_IC((l & (1 << 29)) != 0);
      regs.setR_IC((l & (1 << 30)) != 0);
      regs.setW_SP((l & (1L << 31)) != 0);
      regs.setR_SP((l & (1L << 32)) != 0);
      regs.setSP_LHselect((l & (1L << 33)) != 0);
      regs.setW_RSDR((l & (1L << 34)) != 0);
      regs.setR_RSDR((l & (1L << 35)) != 0);
      regs.setW_Reg((l & (1L << 36)) != 0);
      regs.setR_Reg((l & (1L << 37)) != 0);
      regs.setW_RSAR((l & (1L << 38)) != 0);
      regs.setxyMode((l & (1L << 39)) != 0);
      START = false;
    }

  /* sometimes write IR to CSAR */
  public void ClockCycleRead(int clock)
    {
      if (R_CSAR) 
	{
	  CSAR = ir.getCS();
	  if (CLOCK) 
	    { CSAR = 65; bus.setValue(INT_TYPE); CLOCK = false; }
	}
      if (mem.ClockInterrupt())
	{ 
	  INT_TYPE = 2; CLOCK = true;
	}
    }
}
