package de.unifr.acp.test;

public class TestConcreteSub extends TestAbstractBase {
    
    @Override
    @de.unifr.acp.runtime.annot.Grant("this.a")
    public Object m1() {
        return ((TestAbstractBase)this).a;
    }
    
    @Override
    @de.unifr.acp.runtime.annot.Grant("this.a")
    public Object m2() {
        return this.a;
    }

    public static void main(String[] args) {
        new TestConcreteSub().m1();
        new TestConcreteSub().m2();
    }
}
