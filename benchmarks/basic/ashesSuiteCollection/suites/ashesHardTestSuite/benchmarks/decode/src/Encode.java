import java.util.Vector;
import java.util.Random;

public class Encode
{
  public static void main(String arguments[])
  {
    try
    {
      Vector input = new Vector();
      for (int c = System.in.read(); c != -1; c = System.in.read())
        input.addElement(new Digit((char) c));

      int numShares = Integer.parseInt(arguments[0]);

      char[][] output = new char[input.size()][Digit.MODULUS-1];

      // for each input character
      for (int i = 0; i < input.size(); i++)
      {
        // generate random polynomial
        Digit constTerm = (Digit) input.elementAt(i);
        RandomPolynomial p = new RandomPolynomial(numShares-1, constTerm);

        // evaluate at all values != 0 in Z mod MODULUS
        for (int j = 1; j < Digit.MODULUS; j++)
          output[i][j-1] = p.EvaluateAt(new Digit(j)).CharValue();
      }

      for (int j = 1; j < Digit.MODULUS; j++)
      {
        System.out.print(Digit.CharValue(j) + ": [");
        for (int i = 0; i < input.size(); i++)
          System.out.print(output[i][j-1]);
        System.out.println("]");
      }
    }
    catch (Exception e)
    {
      System.err.println(e);
    }
  }
}

class RandomPolynomial
{
  public RandomPolynomial(int order, Digit constTerm)
  {
    _terms = new Digit[order+1];
    _terms[0] = constTerm;
    Random rand = new Random(System.currentTimeMillis());
    for (int i = 1; i <= order; i++)
      _terms[i] = new Digit(rand.nextInt(Digit.MODULUS));
  }

  public Digit EvaluateAt(Digit d)
  {
    Digit power  = new Digit(1);
    Digit result = new Digit(0);
    for (int i = 0; i < _terms.length; i++)
    {
      result = result.add(_terms[i].mult(power));
      power  = power.mult(d);
    }
    return result;
  }

  private Digit[] _terms;
}
