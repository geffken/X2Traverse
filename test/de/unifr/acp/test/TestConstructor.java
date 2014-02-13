package de.unifr.acp.test;


public class TestConstructor {
    int i;
    int j;
    TestBasics a;
    
    // no permission required for fields of this object in constructor
    @de.unifr.acp.runtime.annot.Grant("@")
    public TestConstructor(@de.unifr.acp.runtime.annot.Grant("a")TestBasics x) {
        this.i = 1;
        this.a = x;
        System.out.println(this.i);
        System.out.println(x.a);
    }
    
    @de.unifr.acp.runtime.annot.Grant("this.(a|i|j).*")
    public void m(@de.unifr.acp.runtime.annot.Grant("a.*") TestBasics x) {
        System.out.println(this.i);
        System.out.println(x.a);
    }
    
    @de.unifr.acp.runtime.annot.Grant("*")
    public static void n(int x, TestBasics[] args2, int y) {
        //System.out.println(args2[1].a);
    }

    public static void main(String[] args) {
        TestConstructor c = new TestConstructor(new TestBasics());
        c.m(new TestBasics());
        n(1, new TestBasics[] {new TestBasics(),new TestBasics()}, 2);
    }

}
