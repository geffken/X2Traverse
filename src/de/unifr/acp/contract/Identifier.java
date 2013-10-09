package de.unifr.acp.contract;

import java.util.Collection;
import java.util.HashSet;

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

}