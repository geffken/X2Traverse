package de.unifr.acp.contract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a conjunction of two or more abstract paths.
 * 
 * @author geffken
 * 
 */
public class And extends Path implements Iterable<Path> {
    
    private final ArrayList<Path> list = new ArrayList<Path>();
    
    public And(And or, Path path) {
        list.addAll(or.list);
        list.add(path);
    }
    
    public And(Path path1, Path path2) {
        list.add(path1);
        list.add(path2);
    }

    @Override
    public And and(Path path) {
        return new And(this, path);
    }

    @Override
    public Iterator<Path> iterator() {
        return list.iterator();
    }
    
    public String toString() {
        String result;
        if (list.size() >= 1) {
            result = "(" + list.get(0).toString();
            for (int i=1; i<list.size(); i++) {
                result += '&';
                result += list.get(i).toString();
            }
            result += ")";
        } else {
            result = "";
        }
        return result;
    }

    @Override
    public boolean isNullable() {
        boolean result = true;
        for (Path path : list) {
            result &= path.isNullable();
        }
        return result;
    }
}
