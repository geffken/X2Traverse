package de.unifr.acp.test;

public class Basics {
    Object a = this; // recursive object structure (tests termination)
    
    @de.unifr.acp.runtime.annot.Grant("this.a.*")
    public void m(@de.unifr.acp.runtime.annot.Grant("a.*") Basics x) {
       Object o = this.a;
        o = x.a;
    }
    
    public static void m() {
        
    }
    
    public void m(int a) {
        
    }
    
    public static void main(String[] args) {
        new Basics().m(new Basics());
    }

}
