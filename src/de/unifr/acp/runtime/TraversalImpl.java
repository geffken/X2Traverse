package de.unifr.acp.runtime;

import static de.unifr.acp.runtime.fst.Permission.intersection;
import static de.unifr.acp.runtime.fst.Permission.union;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.unifr.acp.runtime.fst.Permission;
import de.unifr.acp.runtime.nfa.NFARunner;
import de.unifr.acp.runtime.nfa.ObjectAndNFARunner;

public class TraversalImpl implements Traversal__ {
    // instance variable to keep automaton state
    private NFARunner runner;

    /*
     * a Set<Object x FSTRunner> of already visited pairs
     */
    private Set<ObjectAndNFARunner> visitedPairs = new HashSet<>();
    Map<Object, Map<String, Permission>> locPerms;

    /**
     * Constructor.
     * 
     * @param runner
     *            the FST runner to use for the heap traversal
     * @param locPerms
     *            the location permission to update
     */
    public TraversalImpl(NFARunner runner,
            Map<Object, Map<String, Permission>> locPerms) {
        this.runner = runner; // initialize automaton state
        this.locPerms = locPerms;
    }

    public Map<Object, Map<String, Permission>> getLocationPermissions() {
        return locPerms;
    }

    @Override
    public void visit__(Object obj, String fieldName, Object fieldValue) {
        try {
            // remember automaton state
            NFARunner currentRunner = this.runner.clone();

            // get field permissions for object under consideration
            final Map<String, Permission> fp = getOrCreateFieldPerms(locPerms,
                    obj);

            // step automaton according to field name
            // (for now contracts contain unqualified field names)
            Permission newPerm = runner
                    .stepPerm(unqualifiedFieldNameFromFieldName(fieldName));
            // System.out.println("runner contract :"+runner.getMachine().getContracts());
            // System.out.println("NEW PERM :"+newPerm +
            // " for field "+fieldName);

            // calculate effective permission from installed permission,
            // this automaton's permission and permissions for this object/field
            // from current location permission (or NONE)
            Permission currentLocPerm = fp.get(fieldName);
            if (currentLocPerm == null) {
                currentLocPerm = Permission.NONE;
            }

            Permission resultPerm;
            Permission unionPerm = union(currentLocPerm, newPerm);
            if (unionPerm == Permission.NONE) {
                // avoids evaluation of Global.installedPermissions
                resultPerm = Permission.NONE;
            } else {
                resultPerm = intersection(
                        Global.installedPermission(obj, fieldName), unionPerm);
            }

            // save resulting permission
            fp.put(fieldName, resultPerm);

            // make sure termination
            if (fieldValue == null) {
                return;
            } else if (runner.getStatusQuo().isEmpty()) {
                return; // no chance to reach locations with non-standard
                        // permission - terminate
            } else {
                if (!visitedPairs
                        .add(new ObjectAndNFARunner(fieldValue, runner))) {
                    return; // terminate heap traversal
                }
                try {
                    ((TraversalTarget__) fieldValue).traverse__(this);
                } catch (ClassCastException e) {
                    return; // forced to stop traversal here
                }
            }

            // backtrack to previous automaton state
            this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }

    public static String unqualifiedFieldNameFromFieldName(String fieldName) {
        return fieldName.substring(fieldName.lastIndexOf('.') + 1);
    }

    public Map<String, Permission> getOrCreateFieldPerms(
            Map<Object, Map<String, Permission>> permissions, Object obj) {
        Map<String, Permission> fp = permissions.get(obj);
        if (fp == null) {
            fp = new HashMap<String, Permission>();
            permissions.put(obj, fp);
        }
        return fp;
    }

    @Override
    public void visitPrimitive__(Object obj, String fieldName) {
        try {
            // remember automaton state
            NFARunner currentRunner = this.runner.clone();

            // get field permissions for object under consideration
            final Map<String, Permission> fp = getOrCreateFieldPerms(locPerms,
                    obj);

            // step automaton (copy) according to field name
            // (for now contracts contain unqualified field names)
            Permission newPerm = currentRunner
                    .stepPerm(unqualifiedFieldNameFromFieldName(fieldName));
            // System.out.println("runner contract :"+runner.getMachine().getContracts());
            // System.out.println("NEW PERM :"+newPerm +
            // " for field "+fieldName);

            // calculate effective permission from installed permission,
            // this automaton's permission and permissions for this object/field
            // from current location permission (or NONE)
            Permission currentLocPerm = fp.get(fieldName);
            if (currentLocPerm == null) {
                currentLocPerm = Permission.NONE;
            }

            Permission resultPerm;
            Permission unionPerm = union(currentLocPerm, newPerm);
            if (unionPerm == Permission.NONE) {
                // avoids evaluation of Global.installedPermissions
                resultPerm = Permission.NONE;
            } else {
                resultPerm = intersection(
                        Global.installedPermission(obj, fieldName), unionPerm);
            }
            // System.out.println("CURRENT LOC PERM :"+currentLocPerm +
            // " for field "+fieldName);
            // System.out.println("INSTALLED PERM :"+Global.installedPermission(obj,
            // fieldName) + " for field "+fieldName);
            // System.out.println("RESULT PERM :"+resultPerm +
            // " for field "+fieldName);

            // save resulting permission
            // System.out.println("Old field perm: "+fp);
            fp.put(fieldName, resultPerm);
            // System.out.println("Current field perm: "+fp);
            // System.out.println("----------------------");
            // for (Map.Entry<Object, Map<String, Permission>> entry :
            // locPerms.entrySet()) {
            // System.out.println("ENTRY: "
            // + System.identityHashCode(entry.getKey()) +
            // ", "+entry.getValue());
            // }

            // no need to backtrack to previous automaton state as we haven't
            // modified this.runner
            // this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }

}
