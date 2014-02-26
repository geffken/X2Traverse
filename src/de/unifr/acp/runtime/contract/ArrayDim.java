package de.unifr.acp.runtime.contract;

/**
 * Special (invalid Java) identifier representing one array dimension. E.g. a
 * two dimensional array field can be represented as
 * Id("field").(ArrayDim()).(ArrayDim())
 */
public class ArrayDim extends Identifier {
    public ArrayDim() {
        super("[]");
    }
}
