package de.unifr.acp.runtime.contract;

public class Plus extends SuffixOp {
    public Plus(Path path) {
        super(path);
    }

    @Override
    public String name() {
        return "+";
    }
    
    @Override
    public boolean isNullable() {
        return path.isNullable();
    }
}
