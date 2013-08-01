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
    private static final String TRAVERSAL_TARGET = "de.unifr.acp.templates.TraversalTarget__";
    private static final String OBJECT = "java.lang.Object";
    private final CtClass traversalTargetInterface;
    private final CtClass objectClass;

    private final Map<CtClass, Boolean> visited;
    private final Queue<CtClass> pending;

    protected Map<CtClass, Boolean> getVisited() {
        return Collections.unmodifiableMap(visited);
    }

    protected Collection<CtClass> getPending() {
        return Collections.unmodifiableCollection(pending);
    }

    /**
     * Transformer class capable of statically adding heap traversal code to a
     * set of reachable classes.
     * 
     * @param classname
     *            the name of the class forming the starting point for the
     *            reachable classes
     * @throws NotFoundException
     */
    protected TransClass(String classname) throws NotFoundException {
        CtClass clazz = ClassPool.getDefault().get(classname);
        visited = new HashMap<CtClass, Boolean>();
        pending = new LinkedList<CtClass>();
        pending.add(clazz);
        traversalTargetInterface = ClassPool.getDefault().get(TRAVERSAL_TARGET);
        objectClass = ClassPool.getDefault().get(OBJECT);
    }

    /**
     * Transforms all classes that are reachable from the class corresponding
     * to the specified classname.
     * @param the classname of the class spanning a reachable classes tree
     */
    public static void transformHierarchy(String classname)
            throws NotFoundException, IOException, CannotCompileException {
        TransClass tc = new TransClass(classname);
        tc.computeReachableClasses();
        tc.performTransform();
        tc.flushTransform();
    }

    protected void computeReachableClasses() throws NotFoundException,
            IOException, CannotCompileException {
        while (!pending.isEmpty()) {
            CtClass clazz = pending.remove();
            if (!visited.containsKey(clazz)) {
                doTraverse(clazz);
            }
        }
    }
    
    /*
     * Helper method for <code>computeReachableClasses</code>. Traverses the
     * specified target class and adds it to the list of visited classes. Adds
     * all classes the class' fields to queue of pending classes, if not already
     * visited.
     */
    private void doTraverse(CtClass target) throws NotFoundException {
        CtClass superclazz = target.getSuperclass();
        boolean hasSuperclass = !superclazz.equals(objectClass);
        visited.put(target, hasSuperclass);
        if (hasSuperclass) {
            enter(superclazz);
        }
        CtField[] fs = target.getDeclaredFields();
        for (CtField f : fs) {
            CtClass ft = f.getType();
            if (ft.isPrimitive())
                continue;
            if (ft.getName().matches("java\\..*"))
                continue;
            enter(ft);
        }
    }

    /*
     * Helper method for <code>computeReachableClasses</code>. Adds the
     * specified class to queue of pending classes, if not already visited.
     */
    private void enter(CtClass clazz) {
        if (visited.keySet().contains(clazz)) {
            return;
        } else {
            pending.add(clazz);
        }
    }

    /*
     * Transforms all reachable classes.
     */
    protected void performTransform() throws NotFoundException, IOException,
            CannotCompileException {
        for (Entry<CtClass, Boolean> entry : visited.entrySet()) {
            doTransform(entry.getKey(), entry.getValue());
        }
    }
    
    /*
     * Flushes all reachable classes back to disk.
     */
    protected void flushTransform() throws NotFoundException, IOException,
            CannotCompileException {
        for (CtClass tc : visited.keySet()) {
            tc.writeFile();
        }
    }

    public void doTransform(CtClass target, boolean hasSuperclass)
            throws NotFoundException, IOException, CannotCompileException {

        // add: implements TRAVERSALTARGET
        List<CtClass> targetIfs = Arrays.asList(target.getInterfaces());
        Collection<CtClass> newTargetIfs = new HashSet<CtClass>(targetIfs);
        
        // NOTE: Given the equality semantics of CtClass the condition is only
        // valid if the CtClass instance representing the traversal interface is
        // never detached from the ClassPool as this would result in a new
        // CtClass instance representing the traversal interface to be generated
        // by a call to ClassPool.get(...) not being equal to the old instance.
        
        // only generate implementation of traversal interface if not yet present
        if (!newTargetIfs.contains(traversalTargetInterface)) {
            newTargetIfs.add(traversalTargetInterface);
            target.setInterfaces(newTargetIfs.toArray(new CtClass[0]));

            // add: method traverse__
            String methodbody = createBody(target, hasSuperclass);
            CtMethod m = CtNewMethod.make(methodbody, target);
            target.addMethod(m);
        }
    }

    protected static String createBody(CtClass target, boolean hasSuperclass)
            throws NotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append("public void traverse__(Traversal__ t) {\n");
        for (CtField f : target.getDeclaredFields()) {
            CtClass tf = f.getType();
            String fname = f.getName();
            appendVisitorCalls(sb, target, tf, fname);
        }
        if (hasSuperclass) {
            sb.append("super.traverse__(t);\n");
        }
        sb.append('}');
        return sb.toString();
    }

    protected static void appendVisitorCalls(StringBuilder sb, CtClass target,
            CtClass tf, String fname) throws NotFoundException {
        int nesting = 0;
        String index = "";
        while (tf.isArray()) {
            String var = "i" + nesting;
            
            /* generate for header */
            sb.append("for (int " + var + "; ");
            
            // static type of 'this' corresponds to field's declaring class, no cast needed
            sb.append(var + "<this."+fname+index+".length; ");
            sb.append(var + "++");
            sb.append(") ");
            index = index + "[" + var + "]";
            nesting++;
            tf = tf.getComponentType();
        }
        if (tf.isPrimitive()) {
            sb.append("t.visitPrimitive__(\"");
        } else {
            sb.append("t.visit__(\"");
        }
        sb.append(target.getName());
        sb.append('.');
        sb.append(fname);
        sb.append("\"");
        if (!tf.isPrimitive()) {
            // static type of 'this' corresponds to field's declaring class, no cast needed
            sb.append(", this."); 
            sb.append(fname + index);
        }
        sb.append(");\n");
    }

}
