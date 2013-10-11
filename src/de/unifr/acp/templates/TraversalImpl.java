package de.unifr.acp.templates;

import static de.unifr.acp.fst.Permission.union;
import static de.unifr.acp.fst.Permission.intersection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.unifr.acp.fst.FSTRunner;
import de.unifr.acp.fst.ObjectAndFSTRunner;
import de.unifr.acp.fst.Permission;

public class TraversalImpl implements Traversal__ {
    // instance variable to keep automaton state
    FSTRunner runner;
    
    /*
     * a Set<Object x FSTRunner> of already visited pairs
     */
    private static Set<ObjectAndFSTRunner> visitedPairs = new HashSet<ObjectAndFSTRunner>();
    Map<Object, Map<String, Permission>> locPerms;

    /**
     * Constructor.
     * @param runner the FST runner to use for the heap traversal
     * @param locPerms the location permission to update
     */
    public TraversalImpl(FSTRunner runner, Map<Object, Map<String, Permission>> locPerms) {
        this.runner = runner; // initialize automaton state
        this.locPerms = locPerms;
    }
    
    public Map<Object, Map<String, Permission>> getLocationPermissions() {
        return locPerms;
    }

    @Override
    public void visit__(Object obj, String fieldName, Object fieldvalue) {
//        if (!(fieldvalue instanceof TraversalTarget__)) {
//            return;
//        }
        
        try {
            // remember automaton state
            FSTRunner currentRunner = runner.clone();

            // get field permissions for object under consideration
            final Map<String, Permission> fp = getOrCreateFieldPerms(
                    locPerms, obj);

            // step automaton according to field name
            // (for now contracts contain unqualified field names)
            Permission newPerm = runner.step(unqualifiedFieldNameFromFieldName(fieldName));

            // calculate effective permission from installed permission,
            // this automaton's permission and permissions for this object/field
            // from current location permission (or NONE)
            Permission currentLocPerm = fp.containsKey(fieldName) ? fp
                    .get(fieldName) : Permission.NONE;
            Permission resultPerm = intersection(
                    Global.installedPermission(obj, fieldName),
                    union(currentLocPerm, newPerm));

            // save resulting permission
            fp.put(fieldName, resultPerm);

            // make sure termination
            ObjectAndFSTRunner currentObjAndRunner = new ObjectAndFSTRunner(obj, runner);
            if (visitedPairs.contains(currentObjAndRunner)) {    
                return; // terminate heap traversal
            } else {
                visitedPairs.add(currentObjAndRunner);
                if ((fieldvalue instanceof TraversalTarget__)) {
                    ((TraversalTarget__) fieldvalue).traverse__(this);
                }
            }
            
            // backtrack to previous automaton state
            this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }
    
    public static String unqualifiedFieldNameFromFieldName(
            String fieldName) {
        String result = fieldName;
        int lastDotIndex = -1;
        int i = 0;
        while ((i = fieldName.indexOf('.', lastDotIndex+1)) != -1) {
            lastDotIndex = i;
        }
        result = fieldName.substring(lastDotIndex + 1);
        return result;
    }
    
    public Map<String, Permission> getOrCreateFieldPerms(Map<Object, Map<String, Permission>>permissions, Object obj) {
        final Map<String, Permission> fp;
        if (permissions.containsKey(obj)) {
            fp = permissions.get(obj);
        } else {
            fp = new HashMap<String, Permission>();
            permissions.put(obj, fp);
        }
        return fp;
    }

    @Override
    public void visitPrimitive__(Object obj, String fieldName) {
        // TODO Auto-generated method stub
        
    }

}
