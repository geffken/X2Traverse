import java.util.Vector;

public class Decode
{
  public static void main(String arguments[])
  {
    long startTime = System.currentTimeMillis();

    try
    {
      int numShares = Integer.parseInt(arguments[0]);

      Digit[] x = new Digit[numShares];

      // read first input
      x[0] = new Digit((char) System.in.read());
      System.in.read(); // ":"
      System.in.read(); // " "
      System.in.read(); // "["
      Vector p0 = new Vector();
      for (int c = System.in.read(); c != '\n'; c = System.in.read())
        p0.addElement(new Digit((char) c));
      p0.removeElementAt(p0.size()-1); // remove trailing "]"
      int inputLength = p0.size();

      Digit[][] a = new Digit[inputLength][numShares];

      for (int j = 0; j < inputLength; j++)
        a[j][0] = (Digit) p0.elementAt(j);

      // read subsequent input
      for (int i = 1; i < numShares; i++)
      {
        x[i] = new Digit((char) System.in.read());
        System.in.read(); // ":"
        System.in.read(); // " "
        System.in.read(); // "["
        for (int j = 0; j < inputLength; j++)
          a[j][i] = new Digit((char) System.in.read());
        System.in.read(); // "]"
        System.in.read(); // "\n"
      }

      // try to solve for 1..numShares shares
      for (int n = 1; n <= numShares; n++)
      {
         Digit[][] powerMatrix = new Digit[n][n];

         FillPowerMatrix(powerMatrix, x);

         System.out.print(n + " shares, solution = [");
         for (int j = 0; j < inputLength; j++)
           System.out.print(Solve(powerMatrix, a[j]));
         System.out.println("]");
      }
    }
    catch (Exception e)
    {
      System.err.println(e);
    }

//    System.err.println("Elapsed time: " + (System.currentTimeMillis()-startTime));
  }

  //----------------------------
  // Build a matrix of the form:
  //
  //   1 a a^2 a^3 ...
  //   1 b b^2 b^3 ...
  //   1 c c^2 c^3 ...
  //   1 d d^2 d^3 ...
  //   . .  .   .
  //   . .  .   .
  //   . .  .   .
  //----------------------------
  static void FillPowerMatrix(Digit matrix[][], Digit x[])
  {
    int n = matrix[0].length;

    for (int i = 0; i < n; i++)
    {
      matrix[i][0] = new Digit(1);
      for (int j = 1; j < n; j++)
        matrix[i][j] = matrix[i][j-1].mult(x[i]);
    }
  }

  //---------------------------------------------------------
  // Solve the system of equations to obtain t0:
  //
  // | 1 a a^2 a^3 . . . |   |  t0  |   |  x0  |
  // | 1 b b^2 b^3 . . . |   |  t1  |   |  x1  |
  // | 1 c c^2 c^3 . . . |   |  t2  |   |  x2  |
  // | 1 d d^2 d^3 . . . | x |   .  | = |   .  |
  // | . .  .   .        |   |   .  |   |   .  |
  // | . .  .   .        |   |   .  |   |   .  |
  // | . .  .   .        |   | tn-1 |   | xn-1 |
  // 
  // This function implements the algorithm to solve a system
  // of linear equations using a LUP-Decomposition of the
  // original matrix. It is derived from the algorithm by
  // Cormen, Leiserson & Rivest (ch 31).
  //---------------------------------------------------------
  static Digit Solve(Digit powerMatrix[][], Digit a[])
  {
    int n = powerMatrix[0].length;

    Digit[][] L = new Digit[n][n];
    Digit[][] U = new Digit[n][n];
    int[]     P = new int[n];

    LUP_Decompose(powerMatrix, L, U, P);

    Digit[] y = new Digit[n];
    for (int i = 0; i < n; ++i)
    {
      Digit tmp = new Digit(0);
      for (int j = 0; j < i; ++j)
        tmp = tmp.add(L[i][j].mult(y[j]));
      y[i] = a[P[i]].sub(tmp);
    }

    Digit[] b = new Digit[n];
    for (int i = n-1; i >= 0; --i)
    {
      Digit tmp = new Digit(0);
      for (int j = i + 1; j < n; ++j)
        tmp = tmp.add(U[i][j].mult(b[j]));
      b[i] = (y[i].sub(tmp)).div(U[i][i]);
    }

    return b[0];
  }

  //---------------------------------------------------
  // This method is derived from the LUP-Decomposition
  // algorithm from Cormen, Leiserson & Rivest (ch 31).
  //---------------------------------------------------
  static void LUP_Decompose(Digit inMatrix[][], Digit L[][], Digit U[][], int P[])
  {
    int n = inMatrix[0].length;

    // make a local copy of the input matrix
    Digit[][] matrix = new Digit[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        matrix[i][j] = inMatrix[i][j];

    for (int i = 0; i < n; ++i) P[i] = i;

    boolean pivot;
    int new_k = 0;
    for (int k = 0; k < n-1; ++k)
    {
      pivot = false;
      for (int i = k; i < n; ++i)
        if (pivot = (matrix[i][k].IntValue() != 0)) new_k = i;

      if (!pivot)
        throw new RuntimeException("LUP_Decompose: singular matrix");

      // swap P[k], P[new_k]
      int tmp = P[k]; P[k] = P[new_k]; P[new_k] = tmp;

      Digit tmpDigit;
      for (int i = 0; i < n; ++i)
      {
        // swap matrix[k][i], matrix[new_k][i]
        tmpDigit = matrix[k][i]; matrix[k][i] = matrix[new_k][i]; matrix[new_k][i] = tmpDigit;
      }

      for (int i = k + 1; i < n; ++i)
      {
        matrix[i][k] = matrix[i][k].div(matrix[k][k]);
        for (int j = k + 1; j < n; ++j)
          matrix[i][j] = matrix[i][j].sub(matrix[i][k].mult(matrix[k][j]));
      }
    }

    for (int i = 0; i < n; ++i)
      for (int j = 0; j < n; ++j)
      {
        L[i][j] = (i > j)  ? matrix[i][j] : new Digit(0);
        U[i][j] = (i <= j) ? matrix[i][j] : new Digit(0);
      }
  }
}
