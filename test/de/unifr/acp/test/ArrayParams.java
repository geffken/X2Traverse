package de.unifr.acp.test;

public class ArrayParams {


    public void m(int[] ia) {
        int i0 = ia[0];
        ia[1] = i0;
    }
    
    public void m1(Object[] ia2) {
        int i00 = ((int[][])ia2)[0][0];
        ((int[][])ia2)[1][0] = i00;
    }
    
    public void m2(Object[][] oa2) {
        int[] ia00ia = ((ArrayFields[][])oa2)[0][0].ia;
        ((ArrayFields[][])oa2)[0][0].ia = ia00ia;
    }
    
    public void m3(EmptyClass[][][] oa3) {
        oa3[0][0][0] = null;
    }
    
    public static void main(String[] args) {
        int[] ia = { 1, 2, 3 }; // primitive array
        Object[] ia2 = new int[][] {{1}, {2}, {3}}; // non-primitive array
        Object[][] oa2 = new ArrayFields[][] { { null }, { null } };;
        EmptyClass[][][] oa3 = new EmptyClass[][][] { { { null } }};
        
        ArrayParams arrayParams = new ArrayParams();
        arrayParams.m(ia);
        arrayParams.m1(ia2);
        arrayParams.m2(oa2);
        arrayParams.m3(oa3);
    }
}
