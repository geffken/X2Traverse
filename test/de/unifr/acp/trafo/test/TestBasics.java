package de.unifr.acp.trafo.test;

public class TestBasics {
    Object a = this; // recursive object structure (tests termination)
    
    @de.unifr.acp.annot.Grant("this.a.*")
    public void m(@de.unifr.acp.annot.Grant("a.*") TestBasics x) {
        System.out.println(this.a);
        System.out.println(x.a);
    }
    
    public static void m() {
        
    }
    
    public void m(int a) {
        
    }
    
    public static void main(String[] args) {
        new TestBasics().m(new TestBasics());
    }

}
