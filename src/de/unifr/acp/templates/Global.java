package de.unifr.acp.templates;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.unifr.acp.fst.Permission;
import de.unifr.acp.trafo.TransClass;

public class Global {
    static {
        try {
            logger = Logger.getLogger("de.unifr.acp.templates.Global");
            fh = new FileHandler("traverse.log");
            Global.logger.addHandler(Global.fh);
            Global.logger.setLevel(Level.ALL);
        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static Logger logger;
    private static FileHandler fh;

    public static final boolean enableDebugOutput = false;

    /**
     * A permission stack is a stack of (weak identity) maps from (Object x
     * String) to Permission.
     * 
     * Using an array deque as no null elements are required - for
     * contract-less/ignored methods no location permission is pushed/popped.
     */
    public static Deque<Map<Object, Map<String, Permission>>> locPermStack = new ArrayDeque<Map<Object, Map<String, Permission>>>();

    /**
     * A stack of (weak identity) object sets. Each set represents the newly
     * allocated locations since the last active permission was installed.
     * 
     * Using an array deque as no null elements are required - for
     * contract-less/ignored methods no new permission is pushed/popped.
     */
    public static Deque<Set<Object>> newObjectsStack = new ArrayDeque<Set<Object>>();

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
    public static Permission installedPermission(Object obj, String fieldName) {
        if (enableDebugOutput) {
            for (Set<Object> newObjs : Global.newObjectsStack) {
                System.out.println("----------------------");
                for (Object newObj : newObjs) {
                    System.out.println("NEW OBJECT: "
                            + System.identityHashCode(newObj));
                }
            }
            for (Map<Object, Map<String, Permission>> locPerms : Global.locPermStack) {
                System.out.println("----------------------");
                for (Map.Entry<Object, Map<String, Permission>> entry : locPerms.entrySet()) {
                    System.out.println("ENTRY: "
                            + System.identityHashCode(entry.getKey()) + ", "+entry.getValue());
                }
            }
        }
        Deque<Set<Object>> newObjectsStack = Global.newObjectsStack;
        if (!newObjectsStack.isEmpty()) {

            // consider new objects
            if (newObjectsStack.peek().contains(obj)) {
                return Permission.READ_WRITE;
            }

            // consider topmost location permissions;
            // access to unmarked locations is forbidden
            Map<String, Permission> fieldPerm = Global.locPermStack.peek().get(
                    obj);

            // in case there is a field permission map we expect all instance
            // fields to have an entry (once the implementation is completed ;-)
            // assert ((fieldPerm != null) ? fieldPerm.containsKey(fieldName)
            // : true);
            return (fieldPerm != null) ? (fieldPerm.containsKey(fieldName) ? fieldPerm
                    .get(fieldName) : Permission.NONE)
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
    public static Permission installedPermissionStackNotEmpty(Object obj,
            String fieldName) {
        if (enableDebugOutput) {
            System.out.println("installedPermissions(" + obj + ", " + fieldName
                    + ")");
            System.out.println("locPerms: " + locPermStack.peek());
        }
        Deque<Set<Object>> newObjectsStack = Global.newObjectsStack;

        // consider new objects
        if (newObjectsStack.peek().contains(obj)) {
            return Permission.READ_WRITE;
        }

        // consider topmost location permissions;
        // access to unmarked locations is forbidden
        Map<String, Permission> fieldPerm = Global.locPermStack.peek().get(obj);

        // in case there is a field permission map we expect all instance
        // fields to have an entry (once the implementation is completed ;-)
        // assert ((fieldPerm != null) ? fieldPerm.containsKey(fieldName)
        // : true);
        return (fieldPerm != null) ? (fieldPerm.containsKey(fieldName) ? fieldPerm
                .get(fieldName) : Permission.NONE)
                : Permission.NONE;
    }
    
    public static void installPermission(Map<Object, Map<String, Permission>> locPerms) {
        if (enableDebugOutput) {
            System.out.println("----------------------");
            for (Map.Entry<Object, Map<String, Permission>> entry : locPerms
                    .entrySet()) {
                System.out.println("ENTRY: "
                        + System.identityHashCode(entry.getKey()) + ", "
                        + entry.getValue());
            }
        }
        locPermStack.push(locPerms);
        newObjectsStack.push(Collections.newSetFromMap(new de.unifr.acp.util.WeakIdentityHashMap<Object, Boolean>()));
    }
    
    public static void uninstallPermission() {
        locPermStack.pop();
        Set<Object> newLocSinceInstallation = newObjectsStack.pop();
        if (!newObjectsStack.isEmpty()) {
            newObjectsStack.peek().addAll(newLocSinceInstallation);
        }
    }

    public static void addNewObject(Object obj) {
        if (enableDebugOutput) {
            logger.fine("Adding new object " + System.identityHashCode(obj)
                    + " of type "
                    + ((obj != null) ? obj.getClass().getSimpleName() : ""));
            System.out.println("Adding new object "
                    + System.identityHashCode(obj) + " of type "
                    + ((obj != null) ? obj.getClass().getSimpleName() : ""));
        }
        if (!newObjectsStack.isEmpty()) {
            newObjectsStack.peek().add(obj);
        }
    }

    public static void printViolation(Object obj, String qualifiedFieldName,
            String methodName, Permission effectivePerm, Permission requiredPerm) {
        logger.fine("ACCESS VIOLATION in: " + System.identityHashCode(obj)
                + " of type "
                + ((obj != null) ? obj.getClass().getSimpleName() : ""));
        System.out.println("ACCESS VIOLATION:");
        System.out.println("Instance: " + System.identityHashCode(obj)
                + " of type " + obj.getClass().getSimpleName());
        System.out.println("Field: " + qualifiedFieldName);
        System.out.println("Effective permission: " + effectivePerm);
        System.out.println("Required permission: " + requiredPerm);
        System.out.println("in method " + methodName);
        // System.out.println(de.unifr.acp.templates.Global.locPermStack);
        // System.out.println(de.unifr.acp.templates.Global.newObjectsStack);
        for (Set<Object> newObjs : Global.newObjectsStack) {
            System.out.println("----------------------");
            for (Object newObj : newObjs) {
                System.out.println("NEW OBJECT: "
                        + System.identityHashCode(newObj));
            }
        }
        for (Map<Object, Map<String, Permission>> locPerms : Global.locPermStack) {
            System.out.println("----------------------");
            for (Map.Entry<Object, Map<String, Permission>> entry : locPerms.entrySet()) {
                System.out.println("ENTRY: "
                        + System.identityHashCode(entry.getKey()) + ", "+entry.getValue());
            }
        }
        System.out.println();
    }
}
