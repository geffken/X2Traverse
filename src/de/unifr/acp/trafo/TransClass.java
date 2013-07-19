package de.unifr.acp.trafo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
	private final CtClass traversalTargetInterface;
	private final CtClass OBJECT;
	
	private Set<CtClass> visited;
	private Queue<CtClass> pending;
	
	protected TransClass(CtClass clazz) throws NotFoundException {
		visited = new HashSet<CtClass>();
		pending = new LinkedList<CtClass>();
		pending.add(clazz);
		traversalTargetInterface = ClassPool.getDefault().get(TRAVERSALTARGET);
		OBJECT = ClassPool.getDefault().get("Object");
	}
	
	public static void transformHierarchy(String classname) throws NotFoundException, IOException, CannotCompileException {
		CtClass clazz = ClassPool.getDefault().get(classname);
		new TransClass(clazz).startTransform();
	}

	protected void startTransform() throws NotFoundException, IOException, CannotCompileException {
		while(!pending.isEmpty()) {
			CtClass clazz = pending.remove();
			doTransform(clazz);
		}
	}
	
	protected void enter(CtClass clazz) {
		if (visited.contains(clazz) || pending.contains(clazz)) {
			return;
		} else {
			pending.add(clazz);
		}
	}

	public void doTransform(CtClass target) throws NotFoundException, IOException, CannotCompileException {
		// add: implements TRAVERSALTARGET
		List<CtClass> targetIfs = Arrays.asList(target.getInterfaces());
		Collection<CtClass> newTargetIfs = new LinkedList<CtClass>(targetIfs);
		newTargetIfs.add(traversalTargetInterface);
		target.setInterfaces(newTargetIfs.toArray(new CtClass[0]));
		// investigate superclass
		CtClass superclazz = target.getSuperclass();
		boolean hasSuperclass = !superclazz.equals(OBJECT);
		if (hasSuperclass) {
			enter(superclazz);
		}
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
		target.writeFile();
	}

	protected static String createBody(CtClass target, boolean hasSuperclass) throws NotFoundException {
		StringBuilder sb = new StringBuilder();
		sb.append("public void traverse__(Traversal__ t) {\n");
		for(CtField f : target.getDeclaredFields()) {
			CtClass tf = f.getType();
			String fname = f.getName();
			if (!tf.isPrimitive()) {
				sb.append("if (this." + fname + " instanceof " + TRAVERSALTARGET + ") ");
				sb.append("t.visit__(\"");
				sb.append(fname);
				sb.append("\", this.");
				sb.append(fname);
				sb.append(");\n");
			}
		}
		if (hasSuperclass) {
			sb.append("super.traverse__(t);\n");
		}
		sb.append('}');
		return sb.toString();
	}

}
