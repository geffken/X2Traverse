package de.unifr.acp.trafo;

public class TestEmptyClassWithAnnotatedMethod {
    
    @de.unifr.acp.annot.Grant("this.a.*")
    public void m(@de.unifr.acp.annot.Grant("b.*") Object x) {
        
    }
    
    public static void m() {
        
    }
    
    public void m(int a) {
        
    }

}
