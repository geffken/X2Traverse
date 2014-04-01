package de.unifr.acp.trafo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unifr.acp.runtime.ACPException;
import de.unifr.acp.runtime.TraversalTarget__;
import de.unifr.acp.test.AbstractBase;
import de.unifr.acp.test.ArrayFields;
import de.unifr.acp.test.Basics;
import de.unifr.acp.test.CompoundClass;
import de.unifr.acp.test.CompoundSubclass;
import de.unifr.acp.test.ConcreteSub;
import de.unifr.acp.test.Constructor;
import de.unifr.acp.test.EmptyClass;
import de.unifr.acp.test.TestEmptySubclass;
import de.unifr.acp.test.InnerClass;
import de.unifr.acp.test.IntClass;
import de.unifr.acp.test.Statics;
import de.unifr.acp.test.StaticsNeg;

public class TransClassTest {

    private static ClassPool defaultPool = ClassPool.getDefault();
    private static CtClass objectClass;
    private static final boolean verbose = false;
    private static final String TEST_BASICS_NAME = Basics.class
            .getCanonicalName();
    private static final String TEST_ARRAY_CLASS_NAME = ArrayFields.class
            .getCanonicalName();
    private static final String TEST_EMPTY_CLASS_NAME = EmptyClass.class
            .getCanonicalName();
    private static final String TEST_EMPTY_SUBCLASS_NAME = TestEmptySubclass.class
            .getCanonicalName();
    private static final String TEST_INT_CLASS_NAME = IntClass.class
            .getCanonicalName();
    private static final String TEST_COMPOUND_CLASS_NAME = CompoundClass.class
            .getCanonicalName();
    private static final String TEST_COMPOUND_SUBCLASS_NAME = CompoundSubclass.class
            .getCanonicalName();
    private static final String TEST_CONCONSTRUCTOR_NAME = Constructor.class
            .getCanonicalName();
    private static final String TEST_ABSTRACT_BASE_CLASS_NAME = AbstractBase.class
            .getCanonicalName();
    private static final String TEST_CONCRETE_SUB_CLASS_NAME = ConcreteSub.class
            .getCanonicalName();
    private static final String TEST_INNER_CLASS_NAME = InnerClass.class
            .getCanonicalName();
    private static final String TEST_STATICS_NAME = Statics.class
            .getCanonicalName();
    private static final String TEST_STATICS_NEG_NAME = StaticsNeg.class
            .getCanonicalName();

    // test data

    @BeforeClass
    public static void oneTimeSetup() throws Exception {
        objectClass = ClassPool.getDefault().get("java.lang.Object");
    }

    @Before
    public void setUp() throws Exception {
        defaultPool = ClassPool.getDefault();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testTransClass() {
        fail("Not yet implemented");
    }

    @Test
    public void testTransformHierarchy() {
        fail("Not yet implemented");
    }

    @Test
    public void testStartTransform() {
        fail("Not yet implemented");
    }

    @Test
    public void testDoTransformDoesNotThrow() throws NotFoundException,
            ClassNotFoundException, IOException, CannotCompileException {
        TransClass.transformSingleClass(defaultPool, TEST_BASICS_NAME, false);

        TransClass.transformSingleClass(defaultPool, TEST_ARRAY_CLASS_NAME,
                false);
        TransClass.transformSingleClass(defaultPool,
                TEST_ABSTRACT_BASE_CLASS_NAME, false);
    }

    @Test
    public void testDoTransformAndRunBasics() throws Throwable {
//        TransClass.transformSingleClass(defaultPool, TEST_BASICS_NAME, false);

//        javassist.Loader cl = new javassist.Loader(defaultPool);
        javassist.Loader cl = new APCTransLoader(defaultPool);

        // run with javassist's class loader to enable 'reloading' of test class
        cl.run(TEST_BASICS_NAME, new String[] {});
//        TransClass.defrostReachableClasses(defaultPool, TEST_BASICS_NAME);
    }

    @Test
    public void testDoTransformAndRunArrays() throws Throwable {
//        TransClass.transformSingleClass(defaultPool, TEST_ARRAY_CLASS_NAME,
//                false);
//        javassist.Loader cl = new javassist.Loader(defaultPool);
        javassist.Loader cl = new APCTransLoader(defaultPool);

        // run with javassist's class loader to enable 'reloading' of test class
        cl.run(TEST_ARRAY_CLASS_NAME, new String[] {});

//        TransClass.defrostReachableClasses(defaultPool, TEST_ARRAY_CLASS_NAME);
        CtClass target = defaultPool.get(TraversalTarget__.class
                .getCanonicalName());
        target.rebuildClassFile();
        // target.defrost();
        TransClass.defrostReachableClasses(defaultPool,
                TraversalTarget__.class.getCanonicalName());
    }

    @Test
    public void testDoTransformAndRunConstructor() throws Throwable {
//        TransClass.transformReachableClasses(defaultPool, TEST_CONCONSTRUCTOR_NAME,
//                false);
//        javassist.Loader cl = new javassist.Loader(defaultPool);
        javassist.Loader cl = new APCTransLoader(defaultPool);

        // run with javassist's class loader to enable 'reloading' of test class
        // TransClass.flushClassesToDir(Collections.singleton(defaultPool.get(TEST_CONCONSTRUCTOR_NAME)),
        // "output");
        cl.run(TEST_CONCONSTRUCTOR_NAME, new String[] {});
//        TransClass.defrostReachableClasses(defaultPool, TEST_CONCONSTRUCTOR_NAME);

    }

    @Test
    public void testDoTransformAndRunConcretSubClass() throws Throwable {
//        TransClass.transformReachableClasses(defaultPool,
//                TEST_CONCRETE_SUB_CLASS_NAME, false);
//        javassist.Loader cl = new javassist.Loader(defaultPool);
        javassist.Loader cl = new APCTransLoader(defaultPool);
        cl.addTranslator(defaultPool, new APCDebugPrintTranslator());

        // run with javassist's class loader to enable 'reloading' of test class
        // TransClass.flushClassesToDir(Collections.singleton(defaultPool.get(TEST_CONCONSTRUCTOR_NAME)),
        // "output");
        cl.run(TEST_CONCRETE_SUB_CLASS_NAME, new String[] {});
//        TransClass.defrostReachableClasses(defaultPool, TEST_CONCRETE_SUB_CLASS_NAME);
    }

    @Test
    public void testDoTransformAndRunInnerClass() throws Throwable {
//        TransClass
//                .transformReachableClasses(defaultPool, TEST_INNER_CLASS_NAME, false);
//        javassist.Loader cl = new javassist.Loader(defaultPool);
        javassist.Loader cl = new APCTransLoader(defaultPool);

        // run with javassist's class loader to enable 'reloading' of test class
        // TransClass.flushClassesToDir(Collections.singleton(defaultPool.get(TEST_CONCONSTRUCTOR_NAME)),
        // "output");
        cl.run(TEST_INNER_CLASS_NAME, new String[] {});
        //TransClass.defrostReachableClasses(defaultPool, TEST_INNER_CLASS_NAME);
    }

    @Test
    public void testDoTransformAndRunStaticsClass() throws Throwable {
//        TransClass
//                .transformReachableClasses(defaultPool, TEST_STATICS_NAME, false);
        //javassist.Loader cl = new javassist.Loader(defaultPool);
        javassist.Loader cl = new APCTransLoader(defaultPool);
        //cl.addTranslator(defaultPool, new APCDebugPrintTranslator());

        // run with custom class loader to enable 'reloading
        // and transformation of test class
        cl.run(TEST_STATICS_NAME, new String[] {});
   //     TransClass.defrostReachableClasses(defaultPool, TEST_INNER_CLASS_NAME);
    }
    
    @Test(expected=Exception.class)
    public void testDoTransformAndRunStaticsNegClass() throws Throwable {
        javassist.Loader cl = new APCTransLoader(defaultPool);

        // run with custom class loader to enable 'reloading
        // and transformation of test class
        //assertEquals(1, 2);
        Class<?> exceptionClass = cl.loadClass(ACPException.class.getName());
        cl.run(TEST_STATICS_NEG_NAME, new String[] {});
    }

    @Test
    public void testComputeReachableClasses() throws NotFoundException,
            IOException, CannotCompileException {
        TransClass tc = new TransClass(defaultPool, false);
        List<String> expected = Arrays.asList(TEST_EMPTY_CLASS_NAME);
        checkMap(tc, TEST_EMPTY_CLASS_NAME, expected);

        tc = new TransClass();
        expected = Arrays.asList(TEST_INT_CLASS_NAME);
        checkMap(tc, TEST_INT_CLASS_NAME, expected);

        tc = new TransClass();
        expected = Arrays.asList(TEST_COMPOUND_CLASS_NAME,
                TEST_EMPTY_CLASS_NAME, TEST_INT_CLASS_NAME);
        checkMap(tc, TEST_COMPOUND_CLASS_NAME, expected);

        tc = new TransClass();
        expected = Arrays.asList(TEST_EMPTY_SUBCLASS_NAME,
                TEST_EMPTY_CLASS_NAME);
        checkMap(tc, TEST_EMPTY_SUBCLASS_NAME, expected);

        tc = new TransClass();
        expected = Arrays.asList(TEST_COMPOUND_SUBCLASS_NAME,
                TEST_EMPTY_CLASS_NAME, TEST_INT_CLASS_NAME,
                TEST_COMPOUND_CLASS_NAME);
        checkMap(tc, TEST_COMPOUND_SUBCLASS_NAME, expected);

    }

    /**
     * @param tc
     * @param expected
     * @throws NotFoundException
     * @throws IOException
     * @throws CannotCompileException
     */
    private void checkMap(TransClass tc, String classname, List<String> expected)
            throws NotFoundException, IOException, CannotCompileException {
        Set<CtClass> visited = tc.computeReachableClasses(ClassPool
                .getDefault().get(classname), false);
//        if (verbose) {
//            System.out.println("<<<");
//            for (CtClass cls : visited) {
//                System.out.println(cls.getName());
//            }
//        }
        assertEquals(expected.size(), visited.size());
        for (CtClass cls : visited) {
            assertTrue(expected.contains(cls.getName()));
        }
    }

    private String contentFromFileName(String filename)
            throws FileNotFoundException {
        String path = "testoutputs/" + filename + ".out";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
            return scanner.useDelimiter("\\A").next();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    @Test
    public void testCreateBody() throws NotFoundException,
            FileNotFoundException {
        CtClass target = defaultPool.get(TEST_EMPTY_CLASS_NAME);
        TransClass tc = new TransClass(defaultPool, false);
        String result = tc.createTraverseBody(target, false);
        if (verbose)
            System.out.println(result);
        org.junit.Assert
                .assertEquals(
                        "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\n}",
                        result);

        target = defaultPool.get(TEST_INT_CLASS_NAME);
        result = tc.createTraverseBody(target, false);
        if (verbose)
            System.out.println(result);
        org.junit.Assert
                .assertEquals(
                        "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\nt.visitPrimitive__(this, \"de.unifr.acp.trafo.TestIntClass.myField\");\n}",
                        result);

        result = tc.createTraverseBody(target, true);
        if (verbose)
            System.out.println(result);
        org.junit.Assert
                .assertEquals(
                        "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\nt.visitPrimitive__(this, \"de.unifr.acp.trafo.TestIntClass.myField\");\nsuper.traverse__(t);\n}",
                        result);

        target = defaultPool.get(TEST_COMPOUND_CLASS_NAME);
        result = tc.createTraverseBody(target, false);
        if (verbose)
            System.out.println(result);
        assertEquals(
                "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\nt.visit__(this, \"de.unifr.acp.trafo.TestCompoundClass.x1\", this.x1);\nt.visit__(this, \"de.unifr.acp.trafo.TestCompoundClass.x2\", this.x2);\n}",
                result);

        target = defaultPool.get(TEST_ARRAY_CLASS_NAME);
        result = tc.createTraverseBody(target, false);
        if (verbose)
            System.out.println(result);
        assertEquals(result, contentFromFileName("createBody-TestArrayClass"));

        target = defaultPool.get("java.lang.String");
        result = tc.createTraverseBody(target, false);
        if (verbose)
            System.out.println(result);
        assertEquals(result, contentFromFileName("createBody-String"));
    }

}
