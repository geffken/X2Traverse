package de.unifr.acp.test;

@SuppressWarnings("unused")
public class TestConstructor {
    int i;
    int j;
    TestBasics a;
    
    // no permission required for fields of this object in constructor

    @de.unifr.acp.runtime.annot.Grant("@")
    public TestConstructor(@de.unifr.acp.runtime.annot.Grant("a")TestBasics x) {
        this.i = 1;
        this.a = x;
        int k = this.i;
        Object o = x.a;
    }
    
    @de.unifr.acp.runtime.annot.Grant("this.(a|i|j).*")
    public void m(@de.unifr.acp.runtime.annot.Grant("a.*") TestBasics x) {
        int k = this.i;
        Object o = x.a;
    }
    
    @de.unifr.acp.runtime.annot.Grant("*")
    public static void n(int x, TestBasics[] args2, int y) {
        
    }

    public static void main(String[] args) {
        TestConstructor c = new TestConstructor(new TestBasics());
        c.m(new TestBasics());
        n(1, new TestBasics[] {new TestBasics(),new TestBasics()}, 2);
    }

}
