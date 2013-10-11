package de.unifr.acp.trafo;

public class TestClassWithAnnotatedMethod {
    Object a = new Object();
    
    @de.unifr.acp.annot.Grant("this.a.*")
    public void m(@de.unifr.acp.annot.Grant("b.*") Object x) {
        System.out.println(this.a);
    }
    
    public static void m() {
        
    }
    
    public void m(int a) {
        
    }
    
    public static void main(String[] args) {
        new TestClassWithAnnotatedMethod().m(new Object());
    }

}
