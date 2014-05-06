import java.io.*;import java.util.*;

/**
    LU matrix factorization. (Based on TNT implementation.)
    Decomposes a matrix A  into a lower triangular factor (L)    and an upper triangular factor (U) such that A = L*U.    By convnetion, the main diagonal of L consists of 1's    so that L and U can be stored compactly in a NxN matrix.
*/
public class LU
{
	public static final double num_flops(int N)
	{
		// rougly 2/3*N^3
		double Nd = (double) N;
		return (2.0 * Nd *Nd *Nd/ 3.0);
	}
 
/**
    LU factorization (in place).

    @param A (in/out) On input, the matrix to be factored.
        On output, the compact LU factorization.

    @param pivit (out) The pivot vector records the
        reordering of the rows of A during factorization.
        
    @return 0, if OK, nozero value, othewise.
*/
public static int factor(double A[][],  int pivot[])
{
    int N = A.length;
    int M = A[0].length;

    int minMN = Math.min(M,N);

    for (int j=0; j<minMN; j++)
    {
        // find pivot in column j and  test for singularity.
        int jp=j;
        double t = Math.abs(A[j][j]);
        for (int i=j+1; i<M; i++)
        {
            double ab = Math.abs(A[i][j]);
            if ( ab > t)
            {
                jp = i;
                t = ab;
            }
        }
        pivot[j] = jp;

        // jp now has the index of maximum element 
        // of column j, below the diagonal

        if ( A[jp][j] == 0 )                 
            return 1;       // factorization failed because of zero pivot
        if (jp != j)
        {
            // swap rows j and jp
            double tA[] = A[j];
            A[j] = A[jp];
            A[jp] = tA;
        }
        if (j<M-1)                // compute elements j+1:M of jth column
        {
            // note A(j,j), was A(jp,p) previously which was
            // guarranteed not to be zero (Label #1)
            double recp =  1.0 / A[j][j];
            for (int k=j+1; k<M; k++)
                A[k][j] *= recp;
        }
        if (j < minMN-1)
        {
            // rank-1 update to trailing submatrix:   E = E - x*y;
            //
            // E is the region A(j+1:M, j+1:N)
            // x is the column vector A(j+1:M,j)
            // y is row vector A(j,j+1:N)
            for (int ii=j+1; ii<M; ii++)
            {
                double Aii[] = A[ii];
                double Aj[] = A[j];
                double AiiJ = Aii[j];
                for (int jj=j+1; jj<N; jj++)
                  Aii[jj] -= AiiJ * Aj[jj];
            }
        }
    }

    return 0;
}

	public static double measureLU(int N, double min_cycle, Random R)
	{

		double A[][] = RandomMatrix(N, N, R);
		double lu[][] = new double[N][N];
		int pivot[] = new int[N];		long t=0;		int cycles = 1;
		while(true)
		{			long t1=System.currentTimeMillis();
			for (int i=0; i<cycles; i++)
			{
				CopyMatrix(lu, A);
				LU.factor(lu, pivot);
			}			long t2=System.currentTimeMillis();		     t = t2-t1;
			if ( cycles >= min_cycle) break;
			cycles *= 2;
		}
		// approx Mflops
		double res=LU.num_flops(N) * cycles;		return res;
	}
	private static double[][] RandomMatrix(int M, int N, Random R)
  {
  		double A[][] = new double[M][N];

        for (int i=0; i<N; i++)
			for (int j=0; j<N; j++)
            	A[i][j] = R.nextDouble();
		return A;
	}
	 private static void CopyMatrix(double B[][], double A[][])
  {
        int M = A.length;
        int N = A[0].length;

		int remainder = N & 3;		 // N mod 4;

        for (int i=0; i<M; i++)
        {
            double Bi[] = B[i];
            double Ai[] = A[i];
			for (int j=0; j<remainder; j++)
                Bi[j] = Ai[j];
            for (int j=remainder; j<N; j+=4)
			{
				Bi[j] = Ai[j];
				Bi[j+1] = Ai[j+1];
				Bi[j+2] = Ai[j+2];
				Bi[j+3] = Ai[j+3];
			}
		}
  }
	
public static void main(String args[])throws IOException {BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Please input the size of the matrix:");
String line = "150"; // in.readLine();
System.out.println("Please input the min_cycle:");
String n_time = "80"; // in.readLine();
System.out.println("Please input a random seed:");
String seed = "3244"; // in.readLine();
int N = Integer.parseInt(line);
int R_seed = Integer.parseInt(seed);long T1=System.currentTimeMillis();

double min_time = Double.valueOf(n_time).doubleValue();Random R = new Random(R_seed);
double res = measureLU(N, min_time, R);
//print out results
System.out.println("Floating point operations are:");System.out.println("LU (" + N + "*" + N + "): " + res);
long T2=System.currentTimeMillis();
long T = T2 - T1;//System.out.println("The running time of this system is :"+T+" millis. "); 
 
    }
}


