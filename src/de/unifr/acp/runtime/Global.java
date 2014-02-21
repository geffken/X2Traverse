package de.unifr.acp.runtime;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.unifr.acp.runtime.ACPException;
import de.unifr.acp.runtime.fst.Permission;
import de.unifr.acp.runtime.util.WeakIdentityHashMap;

public class Global {
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

    public static final boolean enableDebugOutput = true;

    /**
     * A permission stack is a stack of (weak identity) maps from (Object x
     * String) to Permission.
     * 
     * Using an array deque as no null elements are required - for
     * contract-less/ignored methods no location permission is pushed/popped.
     */
    public static Deque<Map<Object, Map<String, Permission>>> objPermStack = new ObjectPermissionDequeImpl();

    /**
     * A stack of (weak identity) object sets. Each set represents the newly
     * allocated locations since the last active permission was installed.
     * 
     * Using an array deque as no null elements are required - for
     * contract-less/ignored methods no new permission is pushed/popped.
     */
    public static Deque<Set<Object>> newObjectsStack = new NewObjectDequeImpl();

    /**
     * A map from new objects to the their generations.
     * Objects that are not in the map are considered as untracked objects.
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
     *            the location's fully qualified field name
     * @return the installed permission for the specified location
     */
    public static Permission effectivePermission(Object obj, String fieldName) {
        @Deprecated
        Deque<Set<Object>> newObjectsStack = Global.newObjectsStack;
        Deque<Long> objectGenStack = Global.objectGenStack;
        if (enableDebugOutput) {
            // System.out.println(newObjectsStack);
            // System.out.println(Global.objPermStack);
        }

        Set<Object> topNewObjects = newObjectsStack.peek();
        Long topObjectGen = objectGenStack.peek();

        assert (topNewObjects != null) == (topObjectGen != null);

        // if there is a permission installed
        if (topNewObjects != null) {

            Long objectGen = newObjectGens.get(obj);

            assert (topNewObjects.contains(obj) == (objectGen != null && objectGen >= topObjectGen));

            if (objectGen != null && objectGen >= topObjectGen) {
                return Permission.READ_WRITE;
            }
            
            // consider new objects
            // if (topNewObjects.contains(obj)) {
            // return Permission.READ_WRITE;
            // }

            // consider topmost location permissions;
            // access to unmarked locations is forbidden
            Map<String, Permission> fieldPerm = Global.objPermStack.peek().get(
                    obj);

            // in case there is a field permission map we expect all instance
            // fields to have an entry
            assert ((fieldPerm != null) ? fieldPerm.containsKey(fieldName)
                    : true);
            return (fieldPerm != null) ? (fieldPerm.get(fieldName))
                    : Permission.NONE;

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
     *            the location's fully qualified field name
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
        @Deprecated
        Deque<Set<Object>> newObjectsStack = Global.newObjectsStack;
        Deque<Long> objectGenStack = Global.objectGenStack;

        Set<Object> topNewObjects = newObjectsStack.peek();
        Long topObjectGen = objectGenStack.peek();

        assert (topNewObjects.contains(obj) == (newObjectGens.get(obj) != null && newObjectGens
                .get(obj) >= topObjectGen));

        Long objectGen = newObjectGens.get(obj);
        if (objectGen != null && objectGen >= topObjectGen) {
            return Permission.READ_WRITE;
        }

        // consider new objects
        // if (topNewObjects.contains(obj)) {
        // return Permission.READ_WRITE;
        // }

        // consider topmost location permissions;
        // access to unmarked locations is forbidden
        Map<String, Permission> fieldPerm = Global.objPermStack.peek().get(obj);

        // in case there is a field permission map we expect all instance
        // fields to have an entry (once the implementation is completed ;-)
        assert ((fieldPerm != null) ? fieldPerm.containsKey(fieldName) : true);
        return (fieldPerm != null) ? (fieldPerm.get(fieldName))
                : Permission.NONE;
    }

    public static void installPermissions(
            Map<Object, Map<String, Permission>> objPerms) {
        if (enableDebugOutput) {
            // System.out.println("----------------------");
            // for (Map.Entry<Object, Map<String, Permission>> entry : objPerms
            // .entrySet()) {
            // System.out.println("ENTRY: "
            // + System.identityHashCode(entry.getKey()) + ", "
            // + entry.getValue());
            // }
        }
        objPermStack.push(objPerms);
        newObjectsStack
                .push(Collections
                        .newSetFromMap(new de.unifr.acp.runtime.util.WeakIdentityHashMap<Object, Boolean>()));
        objectGenStack.push(nextFreshContractGeneration++);
    }

    public static void uninstallPermissions() {
        objPermStack.pop();
        Set<Object> newLocSinceInstallation = newObjectsStack.pop();
        if (!newObjectsStack.isEmpty()) {
            newObjectsStack.peek().addAll(newLocSinceInstallation);
        }
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
        if (!newObjectsStack.isEmpty()) {
            newObjectsStack.peek().add(obj);
        }
        if (!objectGenStack.isEmpty()) {
            newObjectGens.put(obj, objectGenStack.peek());
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