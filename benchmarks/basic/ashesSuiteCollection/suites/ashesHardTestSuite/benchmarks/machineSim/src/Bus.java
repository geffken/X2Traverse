/* the bus. must be added last to the updates list, so that
   we only clear the bus after everything else is done. */

public class Bus implements ClockedDevice
{
  private int lowValue = 0;
  private int highValue = 0;

  public String toString()
    { 
      String low = Integer.toHexString(lowValue);
      while (low.length() < 5) low = "0" + low;
      String high = Integer.toHexString(highValue);
      while (high.length() < 5) high = "0" + high;

      return "Bus: "+high+low;
    }

  /* can be accessed either 18 or 36 bits at a time. */
  public int getLow() { return lowValue; }
  public int getHigh() { return highValue; }

  public void setLow(int lowValue) { this.lowValue = lowValue; }
  public void setHigh(int highValue) { this.highValue = highValue; }

  public long getValue() { return lowValue + (highValue<<18); }
  public void setValue(long newValue) 
    { 
      lowValue = (int) newValue & ((1<<18)-1);
      highValue = (int) (newValue >> 18) & ((1<<18)-1);
    }

  public void ClockCycleWrite(int clock) {}

  /* clear the bus */
  public void ClockCycleRead(int clock)
    {
      lowValue = highValue = 0;
    }
}
