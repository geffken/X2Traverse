/* The ALU.  Not very complicated. */

public class ALU implements ClockedDevice
{
  Bus bus;
  SR sr;
  IR ir;

  public ALU (Bus bus, SR sr, IR ir) 
    { this.bus = bus; this.sr = sr; this.ir = ir; }

  /* IRALU: true = IR, false = ALU */
  private boolean W_LALU, W_RALU; 
  private boolean R_ALU, IRALU, ALU_OP, ALU_PLUS2;
  private long Lt_ALU, Rt_ALU, ALUresult;
  private int MC_op;
  private int ALUopstart = 0;

  /* if something changes during a time quanta, StatusChanged goes up. */
  /* this lets us set ALUopstart. */
  private boolean StatusChanged;

  public String toString() 
    {
      return "ALU: W_LALU="+W_LALU+" W_RALU="+W_RALU+
	" R_ALU="+R_ALU+" IRALU="+IRALU+" ALU_OP="+ALU_OP+
	" ALU_PLUS2="+ALU_PLUS2+" Lt_ALU="+Long.toHexString(Lt_ALU)+
	" Rt_ALU="+Long.toHexString(Rt_ALU)+" ALUres="+ALUresult+
	" MC_op="+MC_op+"\n  ALU_opstart="+ALUopstart;
    }

  private void clearGates()
    {
      W_LALU = false; W_RALU = false; R_ALU = false;
      StatusChanged = false;
    }

  public void setMC_op(int op) { MC_op = op; }
  public void setW_LALU(boolean b) { W_LALU = b; }
  public void setW_RALU(boolean b) { W_RALU = b; }
  public void setR_ALU(boolean b) { R_ALU = b; }
  public void setIRALU(boolean b)
    { if (IRALU != b) { StatusChanged = true; } IRALU = b; }

  public void setALU_OP(boolean b)
    {
      if (ALU_OP != b)
	{
	  StatusChanged = true;
	  if (ALUopstart != 0)
	    throw new IllegalOperation("ALU_OP flag changed during ALU op");
	}
      ALU_OP = b;
    }

  public void setALU_PLUS2(boolean b) 
    { if (b != ALU_PLUS2) StatusChanged = true; ALU_PLUS2 = b; } 

  /* put things on the bus */
  public void ClockCycleWrite(int clock)
    {
      if (StatusChanged) ALUopstart = clock;

      /* see if we can assert the result yet */
      if (clock == ALUopstart + 60)
	{
	  int op;
	  long rightop = Rt_ALU;

	  if (!IRALU) /* ALUop from IR */
	    op = ir.getALUop();
	  else        /* ALUop from microcode */
	    {
	      rightop = ALU_PLUS2 ? 2 : Rt_ALU;
	      op = MC_op;
	    }

	  /* do the operations: */
	  switch (op)
	    {
	    default:
	    case 0: /* nop */
	      ALUresult = 0; 
	      break;
	    case 1:
	      ALUresult = Lt_ALU + rightop; break;
	    case 2:
	      ALUresult = Lt_ALU - rightop; break;
	    case 3:
	      ALUresult = Lt_ALU & rightop; break;
	    case 4:
	      ALUresult = Lt_ALU | rightop; break;
	    case 5:
	      ALUresult = Lt_ALU ^ rightop; break;
	    case 6:
	      ALUresult = (0xfffff) ^ rightop; break;
	    }

	  /* set SR as appropriate. */
	  if (!sr.getSHFALU() && sr.getW_SR12())
	    {
	      sr.setSR1(ALUresult > 0);
	      sr.setSR2(ALUresult < 0);
	    }
	  ALUopstart = 0;
	}

      /* read from internal register */
      if (R_ALU) 
	{
	  if (W_LALU || (W_RALU && !ALU_PLUS2))
	    throw new IllegalOperation
	      ("Writing to ALU registers while reading ALU output");
	  bus.setValue(ALUresult);
	}
    }

  /* take things from the bus */
  public void ClockCycleRead(int clock)
    {
      /* handle writing to internal registers */ 
      if (W_LALU) { Lt_ALU = bus.getValue(); ALUopstart = clock+20; }
      if (W_RALU) { Rt_ALU = bus.getValue(); ALUopstart = clock+20; }   

      clearGates();
    }
}
