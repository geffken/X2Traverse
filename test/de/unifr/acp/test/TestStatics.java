package de.unifr.acp.test;

public class TestStatics {
    Object a = null;
    static Object o = null; // recursive object structure (tests termination)

    @de.unifr.acp.runtime.annot.Grant("TestStatics.o.*")
    public void m() {
        System.out.println(TestStatics.o);
    }

    // static method
    public static void ms(@de.unifr.acp.runtime.annot.Grant("a.*") TestBasics x) {
        System.out.println(x.a);
    }
    
    public static void ms(@de.unifr.acp.runtime.annot.Grant("a.*") Object x) {
        //System.out.println(x.a);
    }

    public static void main(String[] args) {
        new TestStatics().m();
        //new TestStatics().ms(new TestBasics());
        new TestStatics().ms(new Object());
    }

}
