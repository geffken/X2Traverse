package de.unifr.acp.test;

@SuppressWarnings("unused")
public class Constructor {
    int i;
    int j;
    Basics a;
    
    // no permission required for fields of this object in constructor

    @de.unifr.acp.runtime.annot.Grant("@")
    public Constructor(@de.unifr.acp.runtime.annot.Grant("a")Basics x) {
        this.i = 1;
        this.a = x;
        int k = this.i;
        Object o = x.a;
    }
    
    @de.unifr.acp.runtime.annot.Grant("this.(a|i|j).*")
    public void m(@de.unifr.acp.runtime.annot.Grant("a.*") Basics x) {
        int k = this.i;
        Object o = x.a;
    }
    
    @de.unifr.acp.runtime.annot.Grant("*")
    public static void n(int x, Basics[] args2, int y) {
        
    }

    public static void main(String[] args) {
        Constructor c = new Constructor(new Basics());
        c.m(new Basics());
        n(1, new Basics[] {new Basics(),new Basics()}, 2);
    }

}
