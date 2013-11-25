/* Main body of program. Nothing interesting here. */
/* Code written by Patrick Lam, machine by Adam Smith and Francois Rivest */
public class Simulator
{
  public static boolean debug = false;
  private static Bus bus;
  private static IR ir;
  private static MainMemory mem;
  private static Registers regs;
  private static SR sr;
  private static ALU alu;
  private static Shifter shifter;
  private static CS cs;

  public static void main(String[] args)
    {
      int clock = 0;
      int Icount = 0;

      if (args.length < 1)
	{
	  System.out.println("Usage: java Simulator <memoryname>");
	  System.exit(1);
	}

      /* initialize components */
      bus = new Bus(); ir = new IR(bus);
      mem = new MainMemory(bus, args[0]);
      regs = new Registers(bus, ir);
      sr = new SR(); 
      alu = new ALU(bus, sr, ir);
      shifter = new Shifter(bus, ir, sr);
      cs = new CS(ir, mem, regs, alu, shifter, sr, bus);

      /* nifty component array */
      ClockedDevice[] devices = { cs, ir, mem, regs, alu, shifter, bus };

      while (true)
	{
	  /* put things on the bus */
	  for (int i = 0; i < devices.length; i++)
	    devices[i].ClockCycleWrite(clock);

	  /* produce output */
	  if (cs.isFetching() && cs.progress && !debug)
	    {
	      String s = Integer.toString(clock);
	      while (s.length() < 5) s = " "+s;
	      System.out.println("T="+s+"; "+regs.userRegs()+" "+
				 sr.userSR()+"\n"+mem.displays()+"\n");
	      Icount++;
	    }

	  /* produce debugging output */
	  if (cs.progress && debug)
	    {
	      System.out.println("Simulating at time "+clock);
	      for (int i = 0; i < devices.length; i++)
		System.out.println(devices[i]);
	      System.out.println(regs.userRegs());
	    }

	  /* read things off the bus */
	  for (int i = 0; i < devices.length; i++)
	    devices[i].ClockCycleRead(clock);

	  /* handle haltage */
	  if (cs.getHalt()) 
	    {
	      /* add one for the HALT instruction! */
	      Icount++;

	      System.out.println("Halt detected at time "+clock);

	      break;
	    }

	  /* time goes on... */
	  clock += 20;
	}
      System.out.println("Execution complete: "+Icount+" instructions run in "
			 +clock+" nanoseconds, yielding speed of "
			 +(float)Icount/clock*1000+" MIPS.");
    }
}
