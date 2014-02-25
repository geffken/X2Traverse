package de.unifr.acp.runtime;

public interface TraversalTarget__ {
    public void traverse__(Traversal__ t);

    // TODO: remove once static traversal change is complete
    // (currently unimplemented in interface implementing classes!
    // Compiles with Javassist though ;-)
    public void traverseStatics__(Traversal__ t);
}
