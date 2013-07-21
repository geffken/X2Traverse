package de.unifr.acp.trafo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class TransClass {
	private static final String TRAVERSALTARGET = "de.unifr.acp.templates.TraversalTarget__";
	private static final String OBJECT = "java.lang.Object";
	private final CtClass traversalTargetInterface;
	private final CtClass objectClass;
	
	private final Map<CtClass,Boolean> visited;
	private final Queue<CtClass> pending;
	
	protected Map<CtClass, Boolean> getVisited() {
		return Collections.unmodifiableMap(visited);
	}

	protected Collection<CtClass> getPending() {
		return Collections.unmodifiableCollection(pending);
	}

	protected TransClass(String classname) throws NotFoundException {
		CtClass clazz = ClassPool.getDefault().get(classname);
		visited = new HashMap<CtClass,Boolean>();
		pending = new LinkedList<CtClass>();
		pending.add(clazz);
		traversalTargetInterface = ClassPool.getDefault().get(TRAVERSALTARGET);
		objectClass = ClassPool.getDefault().get(OBJECT);
	}
	
	public static void transformHierarchy(String classname) throws NotFoundException, IOException, CannotCompileException {
		TransClass tc = new TransClass(classname);
		tc.computeReachableClasses();
		tc.performTransform();
		tc.flushTransform();
	}

	protected void computeReachableClasses() throws NotFoundException, IOException, CannotCompileException {
		while(!pending.isEmpty()) {
			CtClass clazz = pending.remove();
			doTraverse(clazz);
		}
	}
	
	protected void doTraverse(CtClass target) throws NotFoundException {
		CtClass superclazz = target.getSuperclass();
		boolean hasSuperclass = !superclazz.equals(objectClass);
		visited.put(target, hasSuperclass);
		if (hasSuperclass) {
			enter(superclazz);
		}
		CtField[] fs = target.getDeclaredFields();
		for (CtField f : fs) {
			CtClass ft = f.getType();
			if (ft.isPrimitive()) continue;
			if (ft.getName().matches("java\\..*")) continue;
			enter(ft);
		}
	}

	protected void enter(CtClass clazz) {
		if (visited.keySet().contains(clazz) || pending.contains(clazz)) {
			return;
		} else {
			pending.add(clazz);
		}
	}

	protected void performTransform() throws NotFoundException, IOException, CannotCompileException {
		for (Entry<CtClass, Boolean> entry : visited.entrySet()) {
			doTransform(entry.getKey(), entry.getValue());
		}
	}
	
	protected void flushTransform() throws NotFoundException, IOException, CannotCompileException {
		for (CtClass tc : visited.keySet()) {
			tc.writeFile();
		}
	}

	public void doTransform(CtClass target, boolean hasSuperclass) throws NotFoundException, IOException, CannotCompileException {
		// add: implements TRAVERSALTARGET
		List<CtClass> targetIfs = Arrays.asList(target.getInterfaces());
		Collection<CtClass> newTargetIfs = new LinkedList<CtClass>(targetIfs);
		newTargetIfs.add(traversalTargetInterface);
		target.setInterfaces(newTargetIfs.toArray(new CtClass[0]));
		// add: method traverse__
		String methodbody = createBody(target, hasSuperclass);
		CtMethod m;
		try {
			m = CtNewMethod.make(methodbody, target);
			target.addMethod(m);
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static String createBody(CtClass target, boolean hasSuperclass) throws NotFoundException {
		StringBuilder sb = new StringBuilder();
		sb.append("public void traverse__(Traversal__ t) {\n");
		for(CtField f : target.getDeclaredFields()) {
			CtClass tf = f.getType();
			String fname = f.getName();
			createVisitor(sb, tf, 0, "", fname);
		}
		if (hasSuperclass) {
			sb.append("super.traverse__(t);\n");
		}
		sb.append('}');
		return sb.toString();
	}

	protected static void createVisitor(StringBuilder sb, CtClass tf, int nesting, String index, String fname) throws NotFoundException {
		while (tf.isArray()) {
			String var = "i" + nesting;
			sb.append("for (int " + var + ") ");
			index = "[" + var + "]" + index;
			nesting++;
			tf = tf.getComponentType();
		}
		if (tf.isPrimitive()) {
			sb.append("t.visitPrimitive__(\"");
		} else {
			sb.append("t.visit__(\"");
		}
		sb.append(fname);
		sb.append("\", this.");
		sb.append(fname + index);
		sb.append(");\n");		
	}

}
