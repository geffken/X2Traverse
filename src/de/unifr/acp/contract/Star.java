package de.unifr.acp.contract;

public class Star extends SuffixOp {
    
    public Star(Path path) {
        super(path);
    }

    @Override
    public String name() {
        return "*";
    }

}