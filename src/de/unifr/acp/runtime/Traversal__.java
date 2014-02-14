package de.unifr.acp.runtime;

public interface Traversal__ {

    /**
     * Visits a the specified reference field of the specified object.
     * 
     * @param obj
     *            the object containing the specified field (null in case of a
     *            static field)
     * @param fieldName
     *            the fully qualified field name
     * @param fieldvalue
     *            the non-primitive value of the field
     */
    public void visit__(Object obj, String fieldName, Object fieldvalue);


    /**
     * Visits a the specified primitive field of the specified object.
     * 
     * @param obj
     *            the object containing the specified field (null in case of a
     *            static field)
     * @param fieldName
     *            the fully qualified field name
     */
    public void visitPrimitive__(Object obj, String fieldName);
}
