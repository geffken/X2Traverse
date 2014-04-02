package de.unifr.acp.test;

import de.unifr.acp.runtime.annot.Grant;

public class ArrayParams {

    @Grant("@")
    public void m(@Grant("@") int[] ia) {
        int i0 = ia[0];
        ia[1] = i0;
    }
    
    @Grant("@")
    public void m1(@Grant("@") Object[] ia2) {
        int i00 = ((int[][])ia2)[0][0];
        ((int[][])ia2)[1][0] = i00;
    }

    @Grant("@")
    public void m2(@Grant("ia") Object[][] oa2) {
        int[] ia00ia = ((ArrayFields[][])oa2)[0][0].ia;
        ((ArrayFields[][])oa2)[0][0].ia = ia00ia;
    }
    
    @Grant("@")
    public void m3(@Grant("@") EmptyClass[][][] oa3) {
        oa3[0][0][0] = null;
    }
    
    @Grant("@")
    public void m4(@Grant("ia") Object o) {
        int[] ia00ia = ((ArrayFields[][])o)[0][0].ia;
        ((ArrayFields[][])o)[0][0].ia = ia00ia;
    }
    
    @Grant("@")
    public static void main(String[] args) {
        int[] ia = { 1, 2, 3 }; // primitive array
        Object[] ia2 = new int[][] {{1}, {2}, {3}}; // non-primitive array
        Object[][] oa2 = new ArrayFields[][] { { new ArrayFields() },
                { new ArrayFields() } };
        EmptyClass[][][] oa3 = new EmptyClass[][][] { { { null } }};
        
        ArrayParams arrayParams = new ArrayParams();
        arrayParams.m(ia);
        //arrayParams.m1(ia2);
        //arrayParams.m2(oa2);
        //arrayParams.m3(oa3);
        arrayParams.m4(oa2);
    }
}
