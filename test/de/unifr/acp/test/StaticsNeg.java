package de.unifr.acp.test;

import de.unifr.acp.test.StaticsNeg.InnerStatics;

@SuppressWarnings("unused")
public class StaticsNeg {
    static {
        System.out.println(StaticsNeg.class.getCanonicalName()+" initialized.");
    }
    
    Object a = null;
    static Object o = null;
    static Object p = null;

    @de.unifr.acp.runtime.annot.Grant("Statics.o.*")
    public void m() {
        Object o = StaticsNeg.o;
        
        // access field that was not loaded at contract installation time
        o = InnerStatics.o;
    }

    // static method
    public static void ms(@de.unifr.acp.runtime.annot.Grant("a.*") Basics x) {
        Object o = x.a;
    }
    
    public static void ms(@de.unifr.acp.runtime.annot.Grant("a.*") Object x) {

    }

    public static void main(String[] args) {
        new StaticsNeg().m();
        StaticsNeg.ms(new Object());
    }
    
    public static class InnerStatics {
        static {
            System.out.println(InnerStatics.class.getCanonicalName()+" initialized.");
        }
        public static Object o = null;
    }

}