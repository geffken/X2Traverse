/* main memory.  pretty simple, really. */

import java.io.*;
import java.util.*;

public class MainMemory implements ClockedDevice
{
  final int MEMSIZE = 262144;
  final int DISPCOUNT = 20;
  private Bus bus;
  private int memory[] = new int[MEMSIZE];
  private int displays[] = new int[DISPCOUNT];
  private int ndisp;

  public MainMemory(Bus bus, String fname) 
    { 
      this.bus = bus; 
      for (int i = 0; i < MEMSIZE; i++)
	memory[i] = 0;

      /* load memory from disk */
      BufferedReader in = null;

      try
	{ in = new BufferedReader(new FileReader(fname)); }
      catch(FileNotFoundException e)
	{
	  System.err.println("Could not open memory file!");
	  System.exit(1);
	}

      String s;
      int i = 0;

      try
	{
	  while ((s = in.readLine()) != null)
	    {
	      StringTokenizer st = new StringTokenizer(s, " ");
	      while (st.hasMoreTokens())
		memory[i++] = Integer.parseInt(st.nextToken(), 16);
	    }
	}
      catch(IOException e)
	{
	  System.err.println("Error reading memory from disk!");
	  System.exit(1);
	}

      /* load displays from disk */
      try
	{ in = new BufferedReader(new FileReader(fname+".disp")); }
      catch(FileNotFoundException e)
	{
	  System.err.println("Could not open displays!"); 
	  return;
	}

      i = 0;
      try
	{
	  while ((s = in.readLine()) != null)
	    {
	      StringTokenizer st = new StringTokenizer(s, " ");
	      while (st.hasMoreTokens())
		displays[i++] = Integer.parseInt(st.nextToken(), 16);
	    }
	}
      catch(IOException e)
	{ System.err.println("Error reading from displays!"); }
      ndisp = i;
    }

  private int MSAR;
  private long MSDR;

  /* SDmode = true if in single-word mode */
  private boolean W_MSAR, R_MSAR, W_MSDR, R_MSDR;
  private boolean R_MEM, W_MEM;
  private boolean SDmode, MSAR_lsb;

  /* clock cycles at which read, write from main mem start */
  private int readMemStart, writeMemStart;

  private boolean ClockInt;

  public String toString()
    {
      return "Mem: MSAR="+Integer.toHexString(MSAR)+
	" MSDR="+Long.toHexString(MSDR)+" W_MSAR="+W_MSAR+
	" R_MSAR="+R_MSAR+" W_MSDR="+W_MSDR+" R_MSDR="+R_MSDR+
	" R_MEM="+R_MEM+" W_MEM="+W_MEM+" SDmode="+SDmode+
	" MSAR_lsb="+MSAR_lsb+"\n  readMemStart="+readMemStart+
	" writeMemStart="+writeMemStart;
    }

  /* write out displays (as in displays file) */
  public String displays()
    {
      StringBuffer s = new StringBuffer("\t");

      for (int i = 0; i < ndisp; i++)
	{
	  StringBuffer t = new StringBuffer
	    (Integer.toOctalString(memory[displays[i]]));
	  while (t.length() < 5) t = new StringBuffer(" "+t);
	  s.append(Integer.toOctalString(displays[i])+": "+t+" ");
	  if ((i % 6) == 5)
	    s.append("\n\t");
	}
      return s.toString();
    }

  /* accessor methods */
  public void setW_MSAR(boolean b) { W_MSAR = b; }
  public void setR_MSAR(boolean b) { R_MSAR = b; }
  public void setW_MSDR(boolean b) { W_MSDR = b; }
  public void setR_MEM(boolean b) { R_MEM = b; }
  public void setW_MEM(boolean b) { W_MEM = b; }
  public void setR_MSDR(boolean b) { R_MSDR = b; }
  public void setSDmode(boolean b) { SDmode = b; }

  private void clearGates()
    {
      W_MSAR = false; R_MSAR = false; 
      W_MSDR = false; R_MEM = false; W_MEM = false;
      R_MSDR = false; SDmode = false;
    }

  /* read off and clear the clock int flag */
  public boolean ClockInterrupt()
    {
      boolean b = ClockInt;
      ClockInt = false;
      return b;
    }

  /* put things on the bus in CCW */
  public void ClockCycleWrite(int clock)
    {
      if (R_MSAR) bus.setLow(MSAR);

      /* deal with MSDR writing (to bus) */
      if (R_MSDR)
	if (!SDmode)
	  bus.setValue(MSDR);
	else
	  {
	    /* if lsb is on, you get the 18 low bits */
	    if (MSAR_lsb) bus.setValue(MSDR & 0x3ffff);
	    else bus.setValue(MSDR >> 18);
	  }
    }

  /* now read results off from the bus */
  public void ClockCycleRead(int clock)
    {
      /* deal with main memory accesses */
      /* (which are independent of the bus) */
      if (R_MEM)
	{
	  /* note that the timing is 100 so that it's there after 120ns. */
	  if (readMemStart == 0) readMemStart = clock;
	  else if (readMemStart == clock - 100) 
	    { 
	      readMemStart = 0; 
	      MSDR = ((long)memory[MSAR & ~1]<<18L) + memory[MSAR | 1]; 
	      /* if we read from octal 777 then simulate a clock int */
	      if (MSAR == 511)
		ClockInt = true;
	    }
	}
      else
	readMemStart = 0;

      if (W_MEM)
	{
	  if (writeMemStart == 0) writeMemStart = clock;
	  else if (writeMemStart == clock - 100) 
	    { 
	      writeMemStart = 0; 
	      memory[MSAR | 1] = (int)(MSDR & (1<<18L)-1); 
	      memory[MSAR & ~1] = (int)(MSDR >> 18); 
	    }
	}
      else
	writeMemStart = 0;

      /* deal with MSAR writing */
      if (W_MSAR) 
	{
       	  /* sanity check! */
	  if (readMemStart != 0 || writeMemStart != 0)
	    throw new IllegalOperation("MSAR changed during read/write");

	  MSAR = bus.getLow();

	  /* copy the lsb of MSAR to MSAR_lsb */
	  MSAR_lsb = ((MSAR & 1)==1);
	}

      /* deal with MSDR reading */
      if (W_MSDR) MSDR = bus.getValue();

      clearGates();
    }
}
