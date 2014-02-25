package de.unifr.acp.test;

@SuppressWarnings("unused")
public class TestStatics {
    static {
        System.out.println(TestStatics.class.getCanonicalName()+" initialized.");
    }
    
    Object a = null;
    static Object o = null; // recursive object structure (tests termination)

    @de.unifr.acp.runtime.annot.Grant("TestStatics.o.*")
    public void m() {
        Object o = TestStatics.o;
        
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
        new TestStatics().m();
        //new TestStatics().ms(new TestBasics());
        new TestStatics().ms(new Object());
    }
    
    public static class InnerStatics {
        static {
            System.out.println(InnerStatics.class.getCanonicalName()+" initialized.");
        }
        public static Object o = null;
    }

}
