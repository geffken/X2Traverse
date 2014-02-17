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

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitField__(Object obj, String fieldName, Object fieldValue) {
        try {
            // remember automaton state
            NFARunner currentRunner = this.runner.clone();

            stepAutomatonAndSavePermission(obj, fieldName, this.runner);

            // traverses value and make sure termination
            traverseValue__(fieldValue);

            // backtrack to previous automaton state
            this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }

    @Override
    public void visitArrayField__(Object obj, String fieldName,
            Object[] fieldValue) {
        try {
            // remember automaton state
            NFARunner currentRunner = this.runner.clone();

            stepAutomatonAndSavePermission(obj, fieldName, this.runner);

            visitArray__(fieldValue);

            // backtrack to previous automaton state
            this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc) A copy of
     * @link{de.unifr.acp.runtime.Traversal__#visitArrayField__
     * (java.lang.Object, java.lang.String, java.lang.Object[])} that avoids some
     * type tests.
     * 
     * @see de.unifr.acp.runtime.Traversal__#visitArrayField__(java.lang.Object,
     * java.lang.String, java.lang.Object[][])
     */
    @Override
    public void visitArrayField__(Object obj, String fieldName,
            Object[][] fieldValue) {
        try {
            // remember automaton state
            NFARunner currentRunner = this.runner.clone();

            stepAutomatonAndSavePermission(obj, fieldName, this.runner);

            visitArray__(fieldValue);

            // backtrack to previous automaton state
            this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }
    
    @Override
    public void visitArrayField__(Object obj, String fieldName,
            Object[][][] fieldValue) {
        visitArrayField__(obj, fieldName, fieldValue);
    }

    @Override
    public void visitArrayField__(Object obj, String fieldName,
            Object fieldValue) {
        visitArrayField__(obj, fieldName, (Object[]) fieldValue);
    }

    /**
     * Visits an non-primitive array.
     * 
     * @param array
     *            the array
     */
    private void visitArray__(Object[] array/* , boolean isComponentTypeObject */) {
        try {
            // remember automaton state (probably not needed?)
            NFARunner currentRunner = this.runner.clone();

            // CANDO: step here to distinguish array levels in access control

            if (array != null) {
                for (int i = 0; i < array.length; i++) {
                    // CANDO: step here to distinguish array elements in access
                    // control

                    Object arrayElement = array[i];
                    // if (isComponentTypeObject) {
                    try {
                        this.visitArray__((Object[]) arrayElement
                        /* , isComponentTypeObject */);
                    } catch (ClassCastException e) {
                        traverseValue__(arrayElement);
                    }
                    // } else {
                    // traverseValue__(arrayElement);
                    // }
                }
            }

            // backtrack to previous automaton state
            this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }

    private void visitArray__(Object[][] array/* , boolean isComponentTypeObject */) {
        try {
            // remember automaton state (probably not needed?)
            NFARunner currentRunner = this.runner.clone();

            // CANDO: step here to distinguish array levels in access control

            if (array != null) {
                for (int i = 0; i < array.length; i++) {
                    // CANDO: step here to distinguish array elements in access
                    // control

                    Object[] arrayElement = array[i];
                        this.visitArray__(arrayElement/* , isComponentTypeObject */);
                }
            }

            // backtrack to previous automaton state
            this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }

    private void stepAutomatonAndSavePermission(Object obj, String fieldName,
            NFARunner runnerToStep) {
        // get field permissions for object under consideration
        final Map<String, Permission> fp = getOrCreateFieldPerms(locPerms, obj);

        // step automaton according to field name
        // (for now contracts contain unqualified field names)
        Permission newPerm = runnerToStep
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
                    Global.effectivePermission(obj, fieldName), unionPerm);
        }

        // save resulting permission
        fp.put(fieldName, resultPerm);
    }

    private void traverseValue__(Object value) {
        if (value == null) {
            return;
            // } else if (fieldValue.getClass().isArray()) {
            // Class<? extends Object> componentType =
            // fieldValue.getClass();
            // while (componentType.isArray()) {
            // Object[] fieldValueArray = (Object[]) fieldValue;
            // for (int i = 0; i < ((fieldValueArray != null) ?
            // fieldValueArray.length
            // : 0); i++) {
            // fieldValue = fieldValueArray[i];
            // }
            // componentType = componentType.getComponentType();
            // }
        } else if (runner.getStatusQuo().isEmpty()) {
            return; // no chance to reach locations with non-standard
                    // permission - terminate
        } else {
            if (!visitedPairs.add(new ObjectAndNFARunner(value, runner))) {
                return; // terminate heap traversal
            }
            try {
                ((TraversalTarget__) value).traverse__(this);
            } catch (ClassCastException e) {
                // try {
                //
                // } catch (ClassCastException e2) {
                // Object[] array = (Object[]) fieldValue;
                // }
                return; // forced to stop traversal here
            }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitPrimitiveField__(Object obj, String fieldName) {
        try {
            // remember automaton state
            NFARunner currentRunner = this.runner.clone();

            stepAutomatonAndSavePermission(obj, fieldName, currentRunner);

            // no need to backtrack to previous automaton state as we haven't
            // modified this.runner
            // this.runner = currentRunner;
        } catch (CloneNotSupportedException e) {
            // print unexpected exception
            e.printStackTrace();
        }
    }

}
