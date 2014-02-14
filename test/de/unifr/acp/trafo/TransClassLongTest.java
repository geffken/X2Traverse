package de.unifr.acp.trafo;

import javassist.ClassPool;

import org.junit.Test;

import de.unifr.acp.test.TreeNode;

public class TransClassLongTest {
    
    private static final String TREE_NODE_NAME = TreeNode.class
            .getCanonicalName();
    
    @Test
    public void testDoTransformTreeNode() throws Throwable {
        ClassPool defaultPool = ClassPool.getDefault();
        
        TransClass.transformReachableClasses(defaultPool, TREE_NODE_NAME, false);
        // if (target.isModified()) {
        // target.writeFile("bin");
        // }
        javassist.Loader cl = new javassist.Loader(defaultPool);

        // run with javassist's class loader to enable 'reloading' of test class
        cl.run(TREE_NODE_NAME,
                new String[] { "100", "degenerate", "annotated" });
        cl.run(TREE_NODE_NAME, new String[] { "100", "balanced", "annotated" });
        // TreeNode.main(new String[] { "1280", "degenerate", "delegator" });
    }


}
