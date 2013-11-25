/* what everything else builds from. */
public interface ClockedDevice
{
  /* write puts data on the bus, which is picked up by read. */
  public void ClockCycleWrite(int clock);
  public void ClockCycleRead(int clock);
}
