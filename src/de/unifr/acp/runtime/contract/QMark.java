package de.unifr.acp.runtime.contract;

public class QMark extends SuffixOp {

    public QMark(Path path) {
        super(path);
    }

    @Override
    public String name() {
        return "?";
    }
    
    @Override
    public boolean isNullable() {
        return true;
    }

}
