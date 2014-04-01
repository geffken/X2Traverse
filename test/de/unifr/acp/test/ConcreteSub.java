package de.unifr.acp.test;

public class ConcreteSub extends AbstractBase {
    
    @Override
    @de.unifr.acp.runtime.annot.Grant("this.a")
    public Object m1() {
        return ((AbstractBase)this).a;
    }
    
    @Override
    @de.unifr.acp.runtime.annot.Grant("this.a")
    public Object m2() {
        return this.a;
    }

    public static void main(String[] args) {
        new ConcreteSub().m1();
        new ConcreteSub().m2();
    }
}
