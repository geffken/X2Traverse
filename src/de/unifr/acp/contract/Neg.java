package de.unifr.acp.contract;

public class Neg extends Path {
    
    protected final Path path;

    public Neg(Path path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "!" + path.toString();
    }
    
    @Override
    public boolean isNullable() {
        return !path.isNullable();
    }
}
