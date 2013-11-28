/* the registers. */

public class Registers implements ClockedDevice
{
  Bus bus; IR ir;

  /* the registers (apart from R7); initialized to 0 by Java */
  private long regArray[] = new long[7];

  public Registers(Bus bus, IR ir) 
    { 
      this.bus = bus; this.ir = ir; 
    }

  /* gates */
  private boolean W_RSAR, W_RSDR, R_RSDR;
  private boolean xyMode, R_Reg, W_Reg; 
  private boolean R_SP, W_SP, SP_LHselect, R_IC, W_IC;

  /* x, y come from IR; xyMode = false means x, true means y */
  /* LHselect = true for low, false for high */

  private int RSAR;
  private long RSDR;
  private int SP, IC;

  public String toString()
    {
      return "Registers: W_RSAR="+W_RSAR+" W_RSDR="+W_RSDR+
	" R_RSDR="+R_RSDR+" xyMode="+xyMode+" R_Reg="+R_Reg+
	" W_Reg="+W_Reg+" R_SP="+R_SP+" W_SP="+W_SP+
	"\n SP_LHselect="+SP_LHselect+" R_IC="+R_IC+" W_IC="+W_IC+
	" RSAR="+Integer.toHexString(RSAR)+" RSDR="+Long.toHexString(RSDR)+
	" SP="+Integer.toHexString(SP)+" IC="+Integer.toHexString(IC);
    }

  /* little util function */
  public String paddedString(String s, int i)
    {
      while (s.length() < i) s = "0" + s;
      return s;
    }

  /* prettyprinting */
  public String userRegs()
    {
      StringBuffer others = new StringBuffer();

      for (int i = 0; i < 7; i++)
	{
	  others.append(" R"+i+"="+
			paddedString(Long.toOctalString(regArray[i]), 9));
	  if (i % 4 == 3) others.append("\n                      ");
	}

      return 
	"SP="+paddedString(Integer.toOctalString(SP), 3)+
	" IC="+paddedString(Integer.toOctalString(IC), 3)
	+others.toString();
    }

  /* reset gates between cycles */
  private void clearGates()
    {
      W_RSAR = false; W_RSDR = false; R_RSDR = false;
      xyMode = false; R_Reg = false; W_Reg = false; 
      R_IC = false; W_IC = false;
    }

  /* huge mass of accessor functions */
  public void setW_RSAR(boolean b) { W_RSAR = b; }
  public void setW_RSDR(boolean b) { W_RSDR = b; }
  public void setR_RSDR(boolean b) { R_RSDR = b; }
  public void setxyMode(boolean b) { xyMode = b; }
  public void setR_Reg(boolean b) { R_Reg = b; }
  public void setW_Reg(boolean b) { W_Reg = b; }
  public void setR_IC(boolean b) { R_IC = b; }
  public void setW_IC(boolean b) { W_IC = b; }
  public void setR_SP(boolean b) { R_SP = b; }
  public void setW_SP(boolean b) { W_SP = b; }
  public void setSP_LHselect(boolean b) { SP_LHselect = b; }

  /* put RSDR, SP and IC on the bus (if wanted) */
  public void ClockCycleWrite(int clock)
    {
      if (R_RSDR) bus.setValue(RSDR);
      if (R_SP) 
	{
	  if (SP_LHselect)
	    bus.setLow(SP);
	  else
	    bus.setHigh(SP);
	}
      if (R_IC) bus.setLow(IC);
    }

  /* read stuff from the bus into internal registers */
  public void ClockCycleRead(int clock)
    {
      if (W_RSDR) RSDR = bus.getValue();
      if (W_IC) IC = bus.getLow();
      if (W_SP) 
	if (SP_LHselect)
	  SP = bus.getLow();
	else
	  SP = bus.getHigh();

      /* RSAR does not come from the bus, it comes from IR */
      /* (in fact IR is wired to the RSAR, gated on W_RSAR) */
      if (W_RSAR)
	{
	  if (xyMode)
	    RSAR = ir.getY();
	  else
	    RSAR = ir.getX();
	}

      /* miscellanous actions -- not on the bus. */
      if (R_Reg)
	{
	  if (RSAR == 7)
	    RSDR = IC + (SP << 18);
	  else
	    RSDR = regArray[RSAR];
	}

      if (W_Reg)
	{
	  if (RSAR == 7)
	    { IC = (int)(RSDR & ((1<<18)-1)); SP = (int)(RSDR >> 18); }
	  else
	    regArray[RSAR] = RSDR;
	}

      clearGates();
    }
}
