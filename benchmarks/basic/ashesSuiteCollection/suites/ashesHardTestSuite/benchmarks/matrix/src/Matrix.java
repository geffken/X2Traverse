// Matrix.java
/* Modified by Zhifeng Xiao, 2000
  Main change:
add method matgen--generate a square matrix
add method printda-print diagonal elements
*/
// (C) Copyright Sky Coyote, 1996
// Use and distribute as you see fit


public class Matrix
{
	int rows;
	int cols;
	double[] data;

	public Matrix(int r, int c)
	{
	data = new double[r * c];
	rows = r;
	cols = c;

	for (int i = 0; i < rows * cols; i ++) data[i] = 0;
	}

	public double get_elem(int r, int c)
	{
        //for op if (r < 0 || r >= rows) return 0;
        //for opif (c < 0 || c >= cols) return 0;
	return data[c + r * cols];
	}

	public void set_elem(int r, int c, double x)
	{
        //for op if (r < 0 || r >= rows) return;
        //for op if (c < 0 || c >= cols) return;
	data[c + r * cols] = x;
	}
/* remove this method for opt.
	public void print()
	{
	System.out.println("Rows = " + rows + " Cols = " + cols);
	for (int i = 0; i < rows; i ++)
		{
		StringBuffer s = new StringBuffer();
		for (int j = 0; j < cols; j ++)
			{
			s.append(get_elem(i, j));
			if (j < cols - 1) s.append(",\t");
			}
		System.out.println(s.toString());
		}
	}
*/
        public void printda()
	{
        //System.out.println("Rows = " + rows + " Cols = " + cols);
	for (int i = 0; i < rows; i ++)
		{
		StringBuffer s = new StringBuffer();
		for (int j = 0; j < cols; j ++)
			{
                        if (j==i) s.append(get_elem(i, j));
                        //if (j < cols - 1) s.append(",\t");
			}
		System.out.println(s.toString());
		}
	}

	public void matmul(Matrix a, Matrix b)
	{
	if ((a.cols != b.rows) || (a.rows != rows) ||
		(b.cols != cols)) return;

	for (int i = 0; i < rows; i ++)
		for (int j = 0; j < cols; j ++)
			{
			double s = 0;
			for (int k = 0; k < a.cols; k ++)
				s += a.get_elem(i, k) * b.get_elem(k, j);
			set_elem(i, j, s);
			}
	}

	public void copy(Matrix a)
	{
	for (int i = 0; i < rows; i ++)
		for (int j = 0; j < cols; j ++)
			set_elem(i, j, a.get_elem(i, j));
	}

	public void matinv(Matrix a)
	{
	if (a.rows < 1 || a.rows != a.cols ||
		a.rows != rows || a.cols != cols) return;

	if (a.rows == 1)
		{
		set_elem(0, 0, 1 / a.get_elem(0, 0));
		return;
		}

	Matrix b = new Matrix(rows, cols);
	b.copy(a);

	int n = rows;
	for (int i = 0; i < n; i ++)
		for (int j = 0; j < n; j ++)
			if (i == j) set_elem(i, j, 1);
			else set_elem(i, j, 0);

	for (int i = 0; i < n; i ++)
		{

		// find pivot

		double mag = 0;
		int pivot = -1;

		for (int j = i; j < n; j ++)
			{
			double mag2 = Math.abs(b.get_elem(j, i));
			if (mag2 > mag)
				{
				mag = mag2;
				pivot = j;
				}
			}

		// no pivot (error)

		if (pivot == -1 || mag == 0)
			{
			return;
			}

		// move pivot row into position

		if (pivot != i)
			{
			double temp;
			for (int j = i; j < n; j ++)
				{
				temp = b.get_elem(i, j);
				b.set_elem(i, j, b.get_elem(pivot, j));
				b.set_elem(pivot, j, temp);
				}

			for (int j = 0; j < n; j ++)
				{
				temp = get_elem(i, j);
				set_elem(i, j, get_elem(pivot, j));
				set_elem(pivot, j, temp);
				}
			}

		// normalize pivot row

		mag = b.get_elem(i, i);
		for (int j = i; j < n; j ++) b.set_elem(i, j, b.get_elem(i, j) / mag);
		for (int j = 0; j < n; j ++) set_elem(i, j, get_elem(i, j) / mag);

		// eliminate pivot row component from other rows

		for (int k = 0; k < n; k ++)
			{
			if (k == i) continue;

			double mag2 = b.get_elem(k, i);

			for (int j = i; j < n; j ++) b.set_elem(k, j, b.get_elem(k, j) -
				mag2 * b.get_elem(i, j));
			for (int j = 0; j < n; j ++) set_elem(k, j, get_elem(k, j) -
				mag2 * get_elem(i, j));
			}
		}
	}

  public double matgen ()
  {
    double norma;
    int init, i, j;
    
    init = 1325;
    norma = 0.0;
      for (i = 0; i < rows; i++) {
    for (j = 0; j < cols; j++) {
	init = 3125*init % 65536;
        set_elem(i,j,(init +i*j- 32768.0)/16384.0);
        //norma = (a[j][i] > norma) ? a[j][i] : norma;
      }
    }
    
    return norma;
  }
  
/* remove this for opt.
	public void set_rand()
	{
	for (int i = 0; i < rows; i ++)
		for (int j = 0; j < cols; j ++)
			set_elem(i, j, Math.random());
	}
*/

}

