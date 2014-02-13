package de.unifr.acp.runtime.contract;

public class Star extends SuffixOp {
    
    public Star(Path path) {
        super(path);
    }

    @Override
    public String name() {
        return "*";
    }
    
    @Override
    public boolean isNullable() {
        return true;
    }
}