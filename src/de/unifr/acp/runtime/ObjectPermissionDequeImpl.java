package de.unifr.acp.runtime;

import java.util.ArrayDeque;
import java.util.Map;

import de.unifr.acp.runtime.fst.Permission;

public class ObjectPermissionDequeImpl extends
        ArrayDeque<Map<Object, Map<String, Permission>>> implements ObjectPermissionDeque {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map<Object, Map<String, Permission>> locPerms : Global.objPermStack) {
            sb.append("----------------------\n");
            for (Map.Entry<Object, Map<String, Permission>> entry : locPerms
                    .entrySet()) {
                sb.append("ENTRY: " + System.identityHashCode(entry.getKey())
                        + ", " + entry.getValue() + "\n");
            }
        }
        return sb.toString();
    }

}
