package de.unifr.acp.templates;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;

import de.unifr.acp.fst.Permission;

public class Global {

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
     * contract-less/ignored methods no new  permission is pushed/popped.
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
        Deque<Set<Object>> newObjectsStack = Global.newObjectsStack;
        if (!newObjectsStack.isEmpty()) {
            
            // consider topmost set of new objects
            if (newObjectsStack.peek().contains(obj)) {
                return Permission.READ_WRITE;
            }

            // consider topmost location permissions;
            // access to unmarked locations is forbidden
            Map<String, Permission> fieldPerm = Global.locPermStack.peek()
                    .get(obj);

            // in case there is a field permission map we expect all instance
            // fields to have an entry
            assert ((fieldPerm != null) ? fieldPerm.containsKey(fieldName)
                    : true);
            return (fieldPerm != null) ? fieldPerm.get(fieldName)
                    : Permission.NONE;

        } else {
            assert (Global.locPermStack.isEmpty());

            // in case no contract is active resort to R/W permission
            return Permission.READ_WRITE;
        }
    }

}
