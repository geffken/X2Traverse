package de.unifr.acp.contract;

public class Plus extends SuffixOp {
    public Plus(Path path) {
        super(path);
    }

    @Override
    public String name() {
        return "+";
    }
}
