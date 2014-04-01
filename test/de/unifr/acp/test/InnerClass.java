package de.unifr.acp.test;

public class InnerClass {
    Inner a = new Inner();
    
    public static class Inner {
        public Object b = new Object();
    }
    
    @de.unifr.acp.runtime.annot.Grant("this.a.b")
    public Object m1() {
        return ((InnerClass)this).a.b;
    }
    
    @de.unifr.acp.runtime.annot.Grant("this.a.b")
    public Object m2() {
        return this.a.b;
    }

    public static void main(String[] args) {
        new InnerClass().m1();
        new InnerClass().m2();
    }
}
