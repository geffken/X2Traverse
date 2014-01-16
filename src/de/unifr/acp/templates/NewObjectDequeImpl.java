package de.unifr.acp.templates;

import java.util.ArrayDeque;
import java.util.Set;

public class NewObjectDequeImpl extends ArrayDeque<Set<Object>> {

    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Set<Object> newObjs : Global.newObjectsStack) {
            sb.append("----------------------\n");
            for (Object newObj : newObjs) {
                sb.append("NEW OBJECT: "
                        + System.identityHashCode(newObj)+"\n");
            }
        }
        return sb.toString();
    }

}
