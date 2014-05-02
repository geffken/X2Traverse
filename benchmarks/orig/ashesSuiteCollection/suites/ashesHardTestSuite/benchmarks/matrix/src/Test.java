// Test.java

import java.io.*;
import java.util.*;

public class Test
{
    public static void main(String argv[])
    {
    Test app = new Test();
    }

    public Test()
    {
    	int n;

        n=200;
    	Matrix a = new Matrix(n, n);
    	Matrix b = new Matrix(n, n);
    	Matrix c = new Matrix(n, n);
        a.matgen();
        //a.set_rand();
        //System.out.println("Original Matrix");
        //a.print();

    	long t = System.currentTimeMillis();
    	b.matinv(a);
        //System.out.println("Inverse of Original Matrix");
        //b.print();
    	long t2 = System.currentTimeMillis();
    	double s1 = (t2 - t) / 1000.0;

    	t = System.currentTimeMillis();
    	c.matmul(a, b);
        System.out.println("Diagonal elements of the matrix resulted from a matix timing its inverse");
        c.printda();
    	t2 = System.currentTimeMillis();
    	double s2 = (t2 - t) / 1000.0;
//      System.out.println("Time needed to calculate:\n");
//      System.out.println("Inverse: " + s1 + " secs.");
//    	System.out.println("Multiply: " + s2 + " secs.");
    }

}
