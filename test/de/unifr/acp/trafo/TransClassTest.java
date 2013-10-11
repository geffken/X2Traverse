package de.unifr.acp.trafo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransClassTest {

    private static CtClass objectClass;
    private static final boolean verbose = true;
    
    // test data
    
    
    @BeforeClass
    public static void oneTimeSetup() throws Exception {
        objectClass = ClassPool.getDefault().get("java.lang.Object");
    }

    @Before
    public void setUp() throws Exception {

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
    public void testDoTransform() throws NotFoundException,
            ClassNotFoundException, IOException, CannotCompileException {
        TransClass tc = new TransClass("de.unifr.acp.trafo.TestClassWithAnnotatedMethod");
        CtClass target = ClassPool.getDefault().get(
                "de.unifr.acp.trafo.TestClassWithAnnotatedMethod");
        tc.doTransform(target, !target.getSuperclass().equals(objectClass));
        target.writeFile();
        target.defrost();
        
        target = ClassPool.getDefault().get(
                "de.unifr.acp.trafo.TestArrayClass");
        tc.doTransform(target, !target.getSuperclass().equals(objectClass));
        target.writeFile();
        target.defrost();

        fail("Not yet implemented");
    }

    @Test
    public void testComputeReachableClasses() throws NotFoundException,
            IOException, CannotCompileException {
        TransClass tc = new TransClass("de.unifr.acp.trafo.TestEmptyClass");
        List<String> expected = Arrays
                .asList("de.unifr.acp.trafo.TestEmptyClass");
        checkMap(tc, expected);

        tc = new TransClass("de.unifr.acp.trafo.TestIntClass");
        expected = Arrays.asList("de.unifr.acp.trafo.TestIntClass");
        checkMap(tc, expected);

        tc = new TransClass("de.unifr.acp.trafo.TestCompoundClass");
        expected = Arrays.asList("de.unifr.acp.trafo.TestCompoundClass",
                "de.unifr.acp.trafo.TestEmptyClass",
                "de.unifr.acp.trafo.TestIntClass");
        checkMap(tc, expected);

        tc = new TransClass("de.unifr.acp.trafo.TestEmptySubclass");
        expected = Arrays.asList("de.unifr.acp.trafo.TestEmptySubclass",
                "de.unifr.acp.trafo.TestEmptyClass");
        checkMap(tc, expected);

        tc = new TransClass("de.unifr.acp.trafo.TestCompoundSubclass");
        expected = Arrays.asList("de.unifr.acp.trafo.TestCompoundSubclass",
                "de.unifr.acp.trafo.TestEmptyClass",
                "de.unifr.acp.trafo.TestIntClass",
                "de.unifr.acp.trafo.TestCompoundClass");
        checkMap(tc, expected);

    }

    /**
     * @param tc
     * @param expected
     * @throws NotFoundException
     * @throws IOException
     * @throws CannotCompileException
     */
    private void checkMap(TransClass tc, List<String> expected)
            throws NotFoundException, IOException, CannotCompileException {
        tc.computeReachableClasses();
        Map<CtClass, Boolean> visited = tc.getVisited();
        if (verbose) {
            System.out.println("<<<");
            for (CtClass cls : visited.keySet()) {
                System.out.println(cls.getName());
            }
        }
        assertEquals(expected.size(), visited.size());
        for (CtClass cls : visited.keySet()) {
            assertTrue(expected.contains(cls.getName()));
        }
    }

    private boolean checkFile(String result, String filename)
            throws FileNotFoundException {
        String path = "testoutputs/" + filename + ".out";
        String text = new Scanner(new File(path)).useDelimiter("\\A").next();
        if (verbose) {
            System.out.println("<checkFile>");
            System.out.println(path);
            System.out.println(text);
            System.out.println("</checkFile>");
            System.out.println("result.length = " + result.length()
                    + ". text.length = " + text.length());
        }
        return result.equals(text);
    }

    @Test
    public void testCreateBody() throws NotFoundException,
            FileNotFoundException {
        CtClass target = ClassPool.getDefault().get(
                "de.unifr.acp.trafo.TestEmptyClass");
        String result = TransClass.createBody(target, false);
        if (verbose)
            System.out.println(result);
        org.junit.Assert
                .assertEquals(
                        "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\n}",
                        result);

        target = ClassPool.getDefault().get("de.unifr.acp.trafo.TestIntClass");
        result = TransClass.createBody(target, false);
        if (verbose)
            System.out.println(result);
        org.junit.Assert
                .assertEquals(
                        "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\nt.visitPrimitive__(\"de.unifr.acp.trafo.TestIntClass.myField\");\n}",
                        result);

        result = TransClass.createBody(target, true);
        if (verbose)
            System.out.println(result);
        org.junit.Assert
                .assertEquals(
                        "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\nt.visitPrimitive__(\"de.unifr.acp.trafo.TestIntClass.myField\");\nsuper.traverse__(t);\n}",
                        result);

        target = ClassPool.getDefault().get(
                "de.unifr.acp.trafo.TestCompoundClass");
        result = TransClass.createBody(target, false);
        if (verbose)
            System.out.println(result);
        assertEquals(
                "public void traverse__(de.unifr.acp.templates.Traversal__ t) {\nt.visit__(\"de.unifr.acp.trafo.TestCompoundClass.x1\", this.x1);\nt.visit__(\"de.unifr.acp.trafo.TestCompoundClass.x2\", this.x2);\n}",
                result);

        target = ClassPool.getDefault()
                .get("de.unifr.acp.trafo.TestArrayClass");
        result = TransClass.createBody(target, false);
        if (verbose)
            System.out.println(result);
        assertTrue(checkFile(result, "createBody-TestArrayClass"));

        target = ClassPool.getDefault().get("java.lang.String");
        result = TransClass.createBody(target, false);
        if (verbose)
            System.out.println(result);
        assertTrue(checkFile(result, "createBody-String"));
    }

}
