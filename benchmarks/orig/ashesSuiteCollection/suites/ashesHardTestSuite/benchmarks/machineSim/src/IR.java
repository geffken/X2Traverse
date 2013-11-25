/* ir.  special register that wires up to everything else */

public class IR implements ClockedDevice
{
  Bus bus;

  private int ir;
  private boolean W_IR;

  /* misc. stuff */

  public String toString() 
    { return "IR: "+Integer.toHexString(ir)+" (W_IR="+W_IR+")"; }

  public IR(Bus bus) { this.bus = bus; }

  public void setW_IR(boolean b) { W_IR = b; }

  private void clearGates()
    {
      W_IR = false;
    }

  public int getCS() { return ir >> 12; }
  public int getX()  { return (ir >> 8) & 0x07; }
  public int getY()  { return (ir >> 5) & 0x07; }
  public boolean getLR() { return (((ir >> 7) & 1) == 1) ? true : false; }
  public int getb0b1() { return (ir >> 3) & 0x03; }
  public int getALUop() { return (ir >> 2) & 0x07; }
  public int getN()  { return ir & 0x1f; }

  /* we never write IR to the bus! */
  public void ClockCycleWrite(int clock) { }

  public void ClockCycleRead(int clock)
    {
      /* read IR from bus */
      if (W_IR)
	{
	  ir = bus.getHigh();
	}
      clearGates();
    }
}
