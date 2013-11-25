/* data class that holds a CS word 
   (for which no primitive data type suffices, being 128 bits) */
public class CSWord
{
  private int addr;
  private long ops[] = new long[3];

  public int getAddr() { return addr; }
  public void setAddr(int addr) { this.addr = addr; }

  public long getOp(int which) { return ops[which]; }
  public void setOp(int which, long what) { ops[which] = what; }

  public String toString() { return Integer.toString(addr, 16) + "/"+
			       Long.toString(ops[0], 16)+"/"+
			       Long.toString(ops[1], 16)+"/"+
			       Long.toString(ops[2], 16); }
}
