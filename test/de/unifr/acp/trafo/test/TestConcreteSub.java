package de.unifr.acp.trafo.test;

public class TestConcreteSub extends TestAbstractBase {
    
    @Override
    @de.unifr.acp.annot.Grant("this.a")
    public Object m1() {
        return ((TestAbstractBase)this).a;
    }
    
    @Override
    @de.unifr.acp.annot.Grant("this.a")
    public Object m2() {
        return this.a;
    }

    public static void main(String[] args) {
        new TestConcreteSub().m1();
        new TestConcreteSub().m2();
    }
}
