package de.unifr.acp.test;

import de.unifr.acp.test.TestStaticsNeg.InnerStatics;

@SuppressWarnings("unused")
public class TestStaticsNeg {
    static {
        System.out.println(TestStaticsNeg.class.getCanonicalName()+" initialized.");
    }
    
    Object a = null;
    static Object o = null;
    static Object p = null;

    @de.unifr.acp.runtime.annot.Grant("TestStatics.o.*")
    public void m() {
        Object o = TestStaticsNeg.o;
        
        // access field that was not loaded at contract installation time
        o = InnerStatics.o;
    }

    // static method
    public static void ms(@de.unifr.acp.runtime.annot.Grant("a.*") TestBasics x) {
        Object o = x.a;
    }
    
    public static void ms(@de.unifr.acp.runtime.annot.Grant("a.*") Object x) {

    }

    public static void main(String[] args) {
        new TestStaticsNeg().m();
        TestStaticsNeg.ms(new Object());
    }
    
    public static class InnerStatics {
        static {
            System.out.println(InnerStatics.class.getCanonicalName()+" initialized.");
        }
        public static Object o = null;
    }

}