package de.unifr.acp.contract;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of two or more concatenated abstract paths.
 * 
 * @author geffken
 * 
 */
public class Concat extends Path implements Iterable<Path> {
    
    final ArrayList<Path> list = new ArrayList<Path>();
    
    private Concat(Concat concat, Path path) {
        list.addAll(concat.list);
        list.add(path);
    }
    public Concat(Path path1, Path path2) {
        list.add(path1);
        list.add(path2);
    }
    
    @Override
    public Concat concatenate(Path path) {
        return new Concat(this, path);
    }

    @Override
    public Iterator<Path> iterator() {
        return list.iterator();
    }
    
    public String toString() {
        String result;
        if (list.size() >= 1) {
            result = "(" + list.get(0).toString();
            for (int i = 1; i < list.size(); i++) {
                result += '.';
                result += list.get(i).toString();
            }
            result += ")";
        } else {
            result = "";
        }
        return result;
    }
}
