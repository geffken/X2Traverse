package de.unifr.acp.contract;

public abstract class SuffixOp extends Path {
    protected final Path path;
    
    public Path getPath() {
        return path;
    }

    public SuffixOp(Path path) {
        this.path = path;
    }
    
    public String toString() {
        return path.toString() + name();
    }
    
    public abstract String name();
}
