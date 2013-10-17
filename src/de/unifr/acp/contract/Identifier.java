package de.unifr.acp.contract;

public class Identifier extends Path {
    private final String name;

    public String getName() {
        return name;
    }

    public Identifier(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    @Override
    public boolean isNullable() {
        return false;
    }

}