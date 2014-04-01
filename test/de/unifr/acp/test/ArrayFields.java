package de.unifr.acp.test;

public class ArrayFields {
    int[] ia = { 1, 2, 3 }; // primitive array
    Object[] ia2 = new int[][] {{1}, {2}, {3}}; // non-primitive array
    Object[][] oa2 = new ArrayFields[][] { { null }, { null } };;
    EmptyClass[][][] oa3 = new EmptyClass[][][] { { { null } }};

    @de.unifr.acp.runtime.annot.Grant("this.(oa3|ia|ia2), this.oa2.(oa3|ia|ia2)")
    public void m() {
        oa3 = new EmptyClass[1][1][1];
        oa3[0][0][0] = null;
        ia = new int[] { 1, 2, 3, 4 };
        ia2 = new int[][] { {1} };
        ((ArrayFields) oa2[0][0]).oa3[0][0] = null;
        ((ArrayFields) oa2[0][0]).ia = null;
        ((ArrayFields) oa2[0][0]).ia2 = null;
    }

    public void setup() {
        oa2 = new ArrayFields[][] { { new ArrayFields() },
                { new ArrayFields() } };
        ;
    }

    public static void main(String[] args) {
        ArrayFields arrayObject = new ArrayFields();
        arrayObject.setup();
        arrayObject.m();
    }

}
