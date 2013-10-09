package de.unifr.acp.templates;

public interface Traversal__ {
    
    /**
     * Visits a the specified field of the specified object.
     * 
     * @param obj
     *            the object containing the specified field
     * @param fieldName
     *            the fully qualified field name
     * @param fieldvalue
     *            the non-primitive value of the field
     */
    public void visit__(Object obj, String fieldName, Object fieldvalue);
    
    
    public void visitPrimitive__(Object obj, String fieldName);
}
