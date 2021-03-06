package de.unifr.acp.runtime;

public interface Traversal__ {

    /**
     * Visits the specified reference field of the specified object. The field's
     * static type is a proper subtype of Object. However, the field's dynamic
     * value is a known not to be a reference array.
     * It might be a one dimensional primitive array but not a primitive.
     * 
     * @param obj
     *            the object containing the specified field (null in case of a
     *            static field)
     * @param fieldName
     *            the fully qualified field name
     * @param fieldvalue
     *            the non-primitive value of the field
     */
    public void visitField__(Object obj, String fieldName, Object fieldvalue, boolean isFlatVisit);

    /**
     * Visits the specified reference field of the specified object. Only call
     * this method if the dynamic type of the field value can but is not known to
     * be an array (static type is Object). Call
     * {@link #visitField__(Object, String, Object)},
     * {@link #visitPrimitiveField__(Object, String)}, or
     * {@link #visitArrayField__(Object, String, Object[])} otherwise.
     * 
     * @param obj
     *            the object containing the specified field (null in case of a
     *            static field)
     * @param fieldName
     *            the fully qualified field name
     * @param fieldvalue
     *            the non-primitive value of the field
     */
    public void visitPotentialArrayField__(Object obj, String fieldName,
            Object fieldValue, boolean isFlatVisit);

    /**
     * Visits the specified non-primitive array field of the specified object.
     * The static type of the field must be a reference array.
     * 
     * @param obj
     *            the object containing the specified field (null in case of a
     *            static field)
     * @param fieldName
     *            the fully qualified field name
     * @param fieldvalue
     *            the value of the field
     */
    public void visitRefArrayField__(Object obj, String fieldName,
            Object[] fieldValue, boolean isFlatVisit);

    /**
     * Overloaded version as fallback (needed due to Javassist method dispatch
     * limitations - Object[][] is not regarded as subtype of Object[] but of
     * Object).
     * 
     * @param fieldValue
     *            the value of the field of type Object due to method dispatch
     *            limitations in Javassist. The caller needs to make sure this
     *            argument's dynamic type is an Object[]
     * @see Traversal__#visitArrayField__(Object, String, Object[])
     */
    public void visitRefArrayField__(Object obj, String fieldName,
            Object fieldValue, boolean isFlatVisit);

    /**
     * Overloaded version to speed up array traversal.
     * 
     * @param the
     *            value of the field
     * @see Traversal__#visitArrayField__(Object, String, Object[])
     */
    public void visitRefArrayField__(Object obj, String fieldName,
            Object[][] fieldValue, boolean isFlatVisit);

    /**
     * Overloaded version to speed up array traversal.
     * 
     * @param the
     *            value of the field
     * @see Traversal__#visitArrayField__(Object, String, Object[])
     */
    public void visitRefArrayField__(Object obj, String fieldName,
            Object[][][] fieldValue, boolean isFlatVisit);

    /**
     * Visits a the specified primitive field of the
     * specified object. The field's static type is a primitive type.
     * 
     * @param obj
     *            the object containing the specified field (null in case of a
     *            static field)
     * @param fieldName
     *            the fully qualified field name
     */
    public void visitPrimitiveField__(Object obj, String fieldName);
    
    /**
     * Visits the specified non-primitive array.
     * 
     * @param array
     *            the array
     */
    public void visitRefArray__(Object array);
    
    /**
     * Visits the specified object. Only call
     * this method if the dynamic type of the reference value can but is not known to
     * be an array (static type is Object). Call
     * {@link TraversalTarget__#traverse__(Traversal__)},
     * {@link #visitPrimitiveArray__(Object)} otherwise.
     * {@link #visitArray__(Object[])} otherwise.
     */
    public void visitPotentialArray__(Object obj);

    /**
     * Visits the specified primitive array. Only call
     * this method if the static type of the reference value is
     * a primitive array (static type is int[], float[], ...).
     * Call 
     * {@link TraversalTarget__#traverse__(Traversal__)},
     * {@link #visitPotentialArray__(Object)} otherwise.
     * {@link #visitArray__(Object[])} otherwise.
     */
//    public void visitPrimitiveArray__(Object obj);
}
