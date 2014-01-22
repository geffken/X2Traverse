package de.unifr.acp.trafo.test;

public class TestArrayClass {
	TestEmptyClass[][] nestedArray;
	boolean b;
	
	
	public void m() {
	    nestedArray = new TestEmptyClass[1][];
	}
	
    public static void main(String[] args) {
        new TestArrayClass().m();
    }

}
