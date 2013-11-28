/* the shifter.  not implemented by simulating the gates. */

public class Shifter implements ClockedDevice
{
  private Bus bus;
  private IR ir;
  private SR sr;
  private boolean W_ISHFT, R_ISHFT, LR;
  private long ISHF, ISHF_out;

  private int ShifterOpStart = 0;

  public Shifter(Bus bus, IR ir, SR sr) 
    { this.bus = bus; this.ir = ir; this.sr = sr; }

  public void setW_ISHFT(boolean b) { W_ISHFT = b; }
  public void setR_ISHFT(boolean b) { R_ISHFT = b; }

  public String toString()
    {
      return "Shifter: W_ISHFT="+W_ISHFT+" R_ISHFT="+R_ISHFT+
	" LR="+LR+" ISHF="+ISHF+" ISHF_out="+ISHF_out+" ShifterOpStart="+
	ShifterOpStart;
    }

  /* we actually do the shift (in the appropriate time) */
  public void ClockCycleWrite(int clock)
    {
      boolean LR = ir.getLR();
      if (ShifterOpStart+20 >= clock)
	{
	  if (LR)
	    ISHF_out = ISHF << ir.getN();
	  else
	    ISHF_out = ISHF >> ir.getN();

	  /* set SR as appropriate */
	  if (sr.getSHFALU() && sr.getW_SR12())
	    {
	      sr.setSR1(ISHF_out > 0);
	      sr.setSR2(ISHF_out < 0);
	    }
	}
      if (R_ISHFT) bus.setValue(ISHF_out);
    }

  public void ClockCycleRead(int clock)
    {
      if (W_ISHFT) { ISHF = bus.getValue(); ShifterOpStart = clock; }
    }
}
