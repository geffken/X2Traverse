package de.unifr.acp.trafo;

import static org.junit.Assert.*;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransClassTest {
	
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
	public void testEnter() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoTransform() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateBody() throws NotFoundException {
		CtClass target = ClassPool.getDefault().get("de.unifr.acp.trafo.TestEmptyClass");
		String result = TransClass.createBody(target, false);
		org.junit.Assert.assertEquals("public void traverse__(Traversal__ t) {\n}", result);
		
		target = ClassPool.getDefault().get("de.unifr.acp.trafo.TestIntClass");
		result = TransClass.createBody(target, false);
		org.junit.Assert.assertEquals("public void traverse__(Traversal__ t) {\n}", result);

		result = TransClass.createBody(target, true);
		org.junit.Assert.assertEquals("public void traverse__(Traversal__ t) {\nsuper.traverse__(t);\n}", result);
		
		target = ClassPool.getDefault().get("de.unifr.acp.trafo.TestCompoundClass");
		result = TransClass.createBody(target, false);
		
		target = ClassPool.getDefault().get("java.lang.String");
		result = TransClass.createBody(target, false);
		
		System.out.println(result);
	}

}
