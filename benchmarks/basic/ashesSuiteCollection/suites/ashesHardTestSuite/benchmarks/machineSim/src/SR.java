/* Status register(s) */
/* note that this component is *NOT* clocked. */
/* things get wired directly to the SR. */

public class SR
{
  private boolean SR0;

  /* two sets of SR1,2 -- one for interrupts, one not */
  private boolean SR1, SR2;
  private boolean SR1b, SR2b;

  private boolean W_SR12;
  private boolean SHFALU;

  /* everything. */
  public String toString() 
    { 
      return "SR: SR0="+SR0+" SR1n="+SR1+" SR2n="+SR2+
	" SR1i="+SR1b+" SR2i="+SR2b+
	" W_SR12="+W_SR12+" SHFALU="+SHFALU; 
    }

  /* display for non-debugging purposes. rather minimalist. */
  public String userSR()
    {
      return (SR0?"SR0 ":"")+
	(SR1?"SR1b ":"")+
	(SR2?"SR2b ":"")+
	(SR1b?"SR1e ":"")+
	(SR2b?"SR1e ":"");
    }

  /* nothing else interesting here.  set flags, etc. */
  public SR() { SR0 = SR1 = SR2 = false; SHFALU = false; W_SR12 = false; }

  public void setSHFALU(boolean b) { SHFALU = b; }
  public boolean getSHFALU() { return SHFALU; }

  public void setW_SR12(boolean b) { W_SR12 = b; }
  public boolean getW_SR12() { return W_SR12; }

  public boolean getSR0() { return SR0; }
  public void setSR0(boolean b) { SR0 = b; }

  public boolean getSR1() { if (SR0) return SR1; else return SR1b; }
  public boolean getSR2() { if (SR0) return SR2; else return SR2b; }

  public void setSR1(boolean b) 
    { 
      if (!W_SR12) return;
      if (SR0) SR1 = b; else SR1b = b; 
    }
  public void setSR2(boolean b) 
    { 
      if (!W_SR12) return;
      if (SR0) SR2 = b; else SR2b = b; 
    }
}
