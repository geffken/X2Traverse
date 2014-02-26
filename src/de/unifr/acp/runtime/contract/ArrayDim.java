package de.unifr.acp.runtime.contract;

/** Special (invalid Java) identifier representing one array dimension. */
public class ArrayDim extends Identifier {
    public ArrayDim() {
        super("[]");
    }
}
