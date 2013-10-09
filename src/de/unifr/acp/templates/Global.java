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
    public Deque<Map<Object, Map<String, Permission>>> locPermsStack = new ArrayDeque<Map<Object, Map<String, Permission>>>();

    /**
     * A stack of (weak identity) object sets. Each set represents the newly
     * allocated locations since the last active permission was installed.
     * 
     * Using an array deque as no null elements are required - for
     * contract-less/ignored methods no new  permission is pushed/popped.
     */
    public Deque<Set<Object>> newObjectsStack = new ArrayDeque<Set<Object>>();
    
    // possibly one more stack is required for debugging/logging/error messages

}
