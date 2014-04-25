package de.unifr.acp.runtime;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.unifr.acp.runtime.ACPException;
import de.unifr.acp.runtime.fst.Permission;
import de.unifr.acp.runtime.nfa.NFA;
import de.unifr.acp.runtime.nfa.NFARunner;
import de.unifr.acp.runtime.util.WeakIdentityHashMap;

public class Global {
    /* default filters are needed at runtime and compile time and thus kept here */
    public final static String FILTER_TRANSFORM_REGEX_DEFAULT = "(java\\..*)";
    // public final String FILTER_TRANSFORM_REGEX_DEFAULT =
    // "(java\\..*)|(de\\.unifr\\.acp\\.(runtime|templates|contracts|util)\\..*)";
    public final static String FILTER_VISIT_REGEX_DEFAULT = "(java\\..*)|(de\\.unifr\\.acp\\.(runtime|templates|contracts|util)\\..*)";
    private static String filterVisitRegex = FILTER_VISIT_REGEX_DEFAULT;
    private static Map<Class, Boolean> filterEnabledForClassMap = new HashMap<Class, Boolean>();

    static {
        try {
            Global.logger = Logger.getLogger("de.unifr.acp.templates.Global");
            fh = new FileHandler("traverse.log");
            if (Global.enableDebugOutput) {
                Global.logger.addHandler(Global.fh);
            }
            Global.logger.setLevel(Global.enableDebugOutput ? Level.ALL
                    : Level.OFF);

        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static Logger logger;
    private static FileHandler fh;

    public static final boolean enableDebugOutput = false;

    public static Deque<NFA> staticPermDeque = new LinkedList<>();

    /**
     * A permission stack is a stack of (weak identity) maps from (Object x
     * String) to Permission.
     * 
     * Using an array deque as no null elements are required - for
     * contract-less/ignored methods no location permission is pushed/popped.
     */
    public static Deque<Map<Object, Map<String, Permission>>> locPermStack = new ObjectPermissionDequeImpl();

    /**
     * A stack of (weak identity) object sets. Each set represents the newly
     * allocated locations since the last active permission was installed.
     * 
     * Using an array deque as no null elements are required - for
     * contract-less/ignored methods no new permission is pushed/popped.
     */
    //@Deprecated
    //public static Deque<Set<Object>> newObjectsStack = new NewObjectDequeImpl();

    /**
     * A map from new objects to the their generations. Objects that are not in
     * the map are considered as untracked objects.
     */
    public static Map<Object, Long> newObjectGens = new de.unifr.acp.runtime.util.WeakIdentityHashMap<>();

    /**
     * A stack for representing the generation of the installed contracts.
     */
    public static Deque<Long> objectGenStack = new ArrayDeque<>();

    /*
     * The next fresh generation to be used by the next installed contract.
     */
    private static long nextFreshContractGeneration = 0;

    // possibly one more stack is required for debugging/logging/error messages

    /**
     * Returns the installed permission for the specified location. Defaults to
     * {@link Permission#READ_WRITE} if no permissions are enabled.
     * 
     * @param obj
     *            the location's object (null indicates a static field -
     *            unimplemented)
     * @param fieldName
     *            the resolved field's fully qualified field name
     * @return the installed permission for the specified location
     */
    public static Permission effectivePermission(Object obj, String fieldName) {

        Deque<Long> objectGenStack = Global.objectGenStack;
        if (enableDebugOutput) {
            // System.out.println(newObjectsStack);
            // System.out.println(Global.objPermStack);
        }

        Long topObjectGen = objectGenStack.peek();

//        @Deprecated
//        Deque<Set<Object>> newObjectsStack = Global.newObjectsStack;
//        @Deprecated
//        Set<Object> topNewObjects = newObjectsStack.peek();

//        assert (topNewObjects != null) == (topObjectGen != null);

        // if there is a permission installed
        if (topObjectGen != null) {
            return effectivePermission(obj, fieldName,
                    Global.locPermStack.peek(), /*topNewObjects,*/ topObjectGen);
        } else {
            // assert (Global.locPermStack.isEmpty());

            // in case no contract is active resort to R/W permission
            return Permission.READ_WRITE;
        }
    }

    /**
     * Returns the installed permission for the specified location. Defaults to
     * {@link Permission#READ_WRITE} if no permissions are enabled.
     * 
     * @param obj
     *            the location's object (null indicates a static field -
     *            unimplemented)
     * @param fieldName
     *            the resolved field's fully qualified field name
     * @return the installed permission for the specified location
     */
    public static Permission effectivePermissionStackNotEmpty(Object obj,
            String fieldName) {
        if (enableDebugOutput) {
            // System.out.println("installedPermissions(" + obj + ", " +
            // fieldName
            // + ")");
            // System.out.println("locPerms: " + locPermStack.peek());
        }
        Deque<Long> objectGenStack = Global.objectGenStack;
        Long topObjectGen = objectGenStack.peek();

//        @Deprecated
//        Deque<Set<Object>> newObjectsStack = Global.newObjectsStack;
//        assert (newObjectsStack.peek().contains(obj) == (newObjectGens.get(obj) != null && newObjectGens
//                .get(obj) >= topObjectGen));

        // static fields are not accessed on objects
        if (obj != null) {
            Long objectGen = newObjectGens.get(obj);
            if (objectGen != null && objectGen >= topObjectGen) {
                return Permission.READ_WRITE;
            }
        }

        // consider new objects
        // if (topNewObjects.contains(obj)) {
        // return Permission.READ_WRITE;
        // }

        // consider topmost location permissions;
        // access to unmarked locations is forbidden
        Map<String, Permission> fieldPerm = Global.locPermStack.peek().get(obj);

        // handle 'new' static fields
        if (obj == null
                && (fieldPerm == null || !fieldPerm.containsKey(fieldName))) {
            return Permission.READ_WRITE;
        }

        // in case there is a field permission map we expect all instance
        // fields to have an entry (once the implementation is completed ;-)
        assert ((fieldPerm != null) ? fieldPerm.containsKey(fieldName) : true);
        return (fieldPerm != null) ? (fieldPerm.get(fieldName))
                : Permission.NONE;
    }

    /**
     * Returns the installed permission for the specified location. Defaults to
     * {@link Permission#READ_WRITE} if no permissions are enabled.
     * 
     * @param locPerms
     *            the location permission to update
     * @param LocPerms
     *            the effective location permission (can be null in which case
     *            the effective installed location permission is used)
     * @param newObjects
     *            the effective the new object set
     * @param newObjectGen
     *            the effective object generation (determining which objects are
     *            new)
     * @return the installed permission for the specified location
     */
    public static Permission effectivePermission(Object obj, String fieldName,
            Map<Object, Map<String, Permission>> locPerms,
            /*@Deprecated Set<Object> newObjects,*/ Long newObjectGen) {
        if (enableDebugOutput) {
            // System.out.println(newObjectsStack);
            // System.out.println(Global.objPermStack);
        }

//        assert (newObjects != null) == (newObjectGen != null) == (newObjectGens != null);
        assert (obj == null || newObjectGen != null);
        assert (locPerms != null);

        // static fields are not accessed on objects
        if (obj != null) {
            Long objectGen = newObjectGens.get(obj);

//            assert (newObjects.contains(obj) == (objectGen != null && objectGen >= newObjectGen));

            if (objectGen != null && objectGen >= newObjectGen) {
                return Permission.READ_WRITE;
            }
        }

        // consider topmost location permissions;
        // access to unmarked locations is forbidden
        Map<String, Permission> fieldPerm = locPerms.get(obj);

        // handle 'new' static fields
        if (obj == null
                && (fieldPerm == null || !fieldPerm.containsKey(fieldName))) {
            return Permission.READ_WRITE;
        }

        // in case there is a field permission map we expect all instance
        // fields to have an entry
        assert ((fieldPerm != null) ? fieldPerm.containsKey(fieldName) : true);
        return (fieldPerm != null) ? (fieldPerm.get(fieldName))
                : Permission.NONE;

    }

    public static void installPermissions(
            Map<Object, Map<String, Permission>> objPerms, NFA nfa,
            String methodName) {
        staticPermDeque.push(nfa);
        locPermStack.push(objPerms);
//        newObjectsStack
//                .push(Collections
//                        .newSetFromMap(new de.unifr.acp.runtime.util.WeakIdentityHashMap<Object, Boolean>()));
        objectGenStack.push(nextFreshContractGeneration++);
        if (enableDebugOutput) {
            System.out.println("----------- installPermissions (" + methodName
                    + ")-------------");

            // for (Map.Entry<Object, Map<String, Permission>> entry : objPerms
            // .entrySet()) {
            // System.out.println("ENTRY: "
            // + System.identityHashCode(entry.getKey()) + ", "
            // + entry.getValue());
            // }

            Iterator<Map<Object, Map<String, Permission>>> it = locPermStack
                    .iterator();
            while (it.hasNext()) {
                Map<Object, Map<String, Permission>> m = it.next();
                System.out.println("STACK ENTRY " + System.identityHashCode(m)
                        + ", " + m.hashCode());
            }
        }
    }

    public static void uninstallPermissions(String methodName) {
        if (enableDebugOutput) {
            System.out.println("----------- uninstallPermissions ("
                    + methodName + ")-----------");
            Iterator<Map<Object, Map<String, Permission>>> it = locPermStack
                    .iterator();
            while (it.hasNext()) {
                Map<Object, Map<String, Permission>> m = it.next();
                System.out.println("STACK ENTRY " + System.identityHashCode(m)
                        + ", " + m.hashCode());
            }
        }
        staticPermDeque.pop();
        locPermStack.pop();
        
//        @Deprecated
//        Set<Object> newLocSinceInstallation = newObjectsStack.pop();
//        if (!newObjectsStack.isEmpty()) {
//            newObjectsStack.peek().addAll(newLocSinceInstallation);
//        }
        objectGenStack.pop();
    }

    public static void addNewObject(Object obj) {
        if (enableDebugOutput) {
            logger.fine("Adding new object " + System.identityHashCode(obj)
                    + " of type "
                    + ((obj != null) ? obj.getClass().getSimpleName() : ""));
            // System.out.println("Adding new object "
            // + System.identityHashCode(obj) + " of type "
            // + ((obj != null) ? obj.getClass().getSimpleName() : ""));
        }
//        if (!newObjectsStack.isEmpty()) {
//            newObjectsStack.peek().add(obj);
//        }
        if (!objectGenStack.isEmpty()) {
            newObjectGens.put(obj, objectGenStack.peek());
        }

    }

    public static void traverseStaticsOnClassLoad(Class<?> clazz) {
        try {
            Iterator<NFA> staticIt = staticPermDeque.descendingIterator();
            Iterator<Map<Object, Map<String, Permission>>> permStackIt = locPermStack
                    .descendingIterator();
//            @Deprecated
//            Iterator<Set<Object>> newObjectsStackIt = newObjectsStack
//                    .descendingIterator();
            Iterator<Long> objectGenStackIt = objectGenStack
                    .descendingIterator();

            // there was no permission installed before the bottom-most
            Map<Object, Map<String, Permission>> effectiveAllLocPerms = null;
//            @Deprecated
//            Set<Object> effectiveNewObjects = Collections.emptySet();
            Long effectiveObjectGen = Long.MAX_VALUE;
            while (staticIt.hasNext()) {
                NFA nfa = staticIt.next();
                Map<Object, Map<String, Permission>> allLocPerms = permStackIt
                        .next();

                NFARunner runner = new NFARunner(nfa);
                runner.resetAndStep(clazz.getSimpleName());
                TraversalImpl visitorStatics = new TraversalImpl(runner,
                        allLocPerms, effectiveAllLocPerms, /*effectiveNewObjects,*/
                        effectiveObjectGen);
                java.lang.reflect.Method m = clazz.getMethod(
                        "traverseStatics__", new Class[] {
                                de.unifr.acp.runtime.Traversal__.class,
                                boolean.class });

                // traverse static fields only (flat)
                m.invoke(null, new Object[] { visitorStatics, true });
                effectiveAllLocPerms = allLocPerms;
//                effectiveNewObjects = newObjectsStackIt.next();
                effectiveObjectGen = objectGenStackIt.next();
            }
        } catch (java.lang.SecurityException e) {
            e.printStackTrace(); // unexpected exception
        } catch (java.lang.IllegalArgumentException e) {
            e.printStackTrace(); // unexpected exception
        } catch (java.lang.NoSuchMethodException e) {
            e.printStackTrace(); // unexpected exception
        } catch (Exception e) {
            e.printStackTrace(); // unexpected exception
        }
    }

    public static void traverseInitializedStatics(ClassLoader loader,
            NFARunner runner, Map<Object, Map<String, Permission>> allLocPerms) {
        Class<?>[] argClassArray = new Class<?>[] {de.unifr.acp.runtime.Traversal__.class, boolean.class};
        try {
            java.lang.reflect.Field f = ClassLoader.class
                    .getDeclaredField("classes");
            f.setAccessible(true);
            Vector<?> classes = (Vector<?>) f.get(loader);
            int numClasses = classes.size();
            for (int j = 0; j < numClasses; j++) {
                Class<?> clazz = (Class<?>) classes.get(j);
                Boolean filterEnabled = filterEnabledForClassMap.get(clazz);
                if (filterEnabled == null) {
                    filterEnabled = clazz.getName().matches(filterVisitRegex);
                    filterEnabledForClassMap.put(clazz, filterEnabled);
                }
                if (filterEnabled)
                    continue;
                // if (clazz.getName().matches(filterVisitRegex))
                // continue;
                if (clazz.isInterface())
                    continue; // TODO: enable traversal of interface fields

                try {
                    java.lang.reflect.Method m = clazz.getMethod(
                            "traverseStatics__", argClassArray);
                    runner.resetAndStep(clazz.getSimpleName());
                    TraversalImpl visitorStatics = new TraversalImpl(runner,
                            allLocPerms);
                    m.invoke(null, new Object[] { visitorStatics, false });
                } catch (java.lang.SecurityException e) {
                    e.printStackTrace(); // unexpected exception
                } catch (java.lang.IllegalArgumentException e) {
                    e.printStackTrace(); // unexpected exception
                } catch (java.lang.IllegalAccessException e) {
                    e.printStackTrace(); // unexpected exception
                } catch (java.lang.reflect.InvocationTargetException e) {
                    e.printStackTrace(); // unexpected exception
                } catch (java.lang.NoSuchMethodException e) {
                    e.printStackTrace(); // unexpected exception
                }
            }
        } catch (java.lang.IllegalAccessException e) {
            e.printStackTrace(); // unexpected exception
        } catch (java.lang.NoSuchFieldException e) {
            e.printStackTrace(); // unexpected exception
        }
    }

    public static void throwOrPrintViolation(Object obj,
            String qualifiedFieldName, String methodName,
            Permission effectivePerm, Permission requiredPerm,
            boolean disableThrowing) {
        logger.fine("ACCESS VIOLATION in: " + System.identityHashCode(obj)
                + " of type "
                + ((obj != null) ? obj.getClass().getSimpleName() : ""));
        ACPException e = new ACPException(obj, qualifiedFieldName, methodName,
                effectivePerm, requiredPerm);
        if (disableThrowing) {
            System.out.println(e);
        } else {
            throw e;
        }
        // System.out.println("ACCESS VIOLATION:");
        // System.out.println("Instance: " + System.identityHashCode(obj)
        // + " of type " + obj.getClass().getSimpleName());
        // System.out.println("Field: " + qualifiedFieldName);
        // System.out.println("Effective permission: " + effectivePerm);
        // System.out.println("Required permission: " + requiredPerm);
        // System.out.println("in method " + methodName);
        // // System.out.println(newObjectsStack);
        // // System.out.println(objPermStack);

    }
}