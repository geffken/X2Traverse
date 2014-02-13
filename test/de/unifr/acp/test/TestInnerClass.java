package de.unifr.acp.test;

public class TestInnerClass {
    Inner a = new Inner();
    
    public static class Inner {
        public Object b = new Object();
    }
    
    @de.unifr.acp.runtime.annot.Grant("this.a.b")
    public Object m1() {
        return ((TestInnerClass)this).a.b;
    }
    
    @de.unifr.acp.runtime.annot.Grant("this.a.b")
    public Object m2() {
        return this.a.b;
    }

    public static void main(String[] args) {
        new TestInnerClass().m1();
        new TestInnerClass().m2();
    }
}
