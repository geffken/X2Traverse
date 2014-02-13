package de.unifr.acp.runtime;

import de.unifr.acp.runtime.Global;
import de.unifr.acp.runtime.fst.Permission;

public class ACPException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -368923782328410522L;
    
    public ACPException(Object obj, String qualifiedFieldName,
            String methodName, Permission effectivePerm, Permission requiredPerm) {
        super(message(obj, qualifiedFieldName, methodName, effectivePerm, requiredPerm));
        
    }
    
    public static String message(Object obj, String qualifiedFieldName,
            String methodName, Permission effectivePerm, Permission requiredPerm) {
        StringBuilder sb = new StringBuilder();
        sb.append("ACCESS VIOLATION:\n");
        sb.append("Instance: " + System.identityHashCode(obj)
                + " of type " + obj.getClass().getSimpleName() + "\n");
        sb.append("Field: " + qualifiedFieldName+"\n");
        sb.append("Effective permission: " + effectivePerm + "\n");
        sb.append("Required permission: " + requiredPerm + "\n");
        sb.append("in method " + methodName + "\n");
        sb.append(Global.newObjectsStack+"\n");
        sb.append(Global.objPermStack+"\n");
        sb.append("\n");
        return sb.toString();
    }    

}
