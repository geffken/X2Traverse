/* for our (rather sparse) error checks */
public class IllegalOperation extends RuntimeException
{
  public IllegalOperation(String s)
    {
      System.err.println("Illegal Operation exception thrown: "+s);
    }
}
