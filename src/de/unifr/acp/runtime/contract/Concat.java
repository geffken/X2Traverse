package de.unifr.acp.runtime.contract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents am immutable list of two or more concatenated abstract paths.
 * 
 * @author geffken
 * 
 */
public class Concat extends Path implements List<Path> {
    
    private final ArrayList<Path> list = new ArrayList<Path>();
    
    private Concat(Concat concat, Path path) {
        list.addAll(concat);
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
    
    @Override
    public boolean isNullable() {
        boolean result = true;
        for (Path path : list) {
            result &= path.isNullable();
        }
        return result;
    }
    
    @Override
    public int size() {
        return list.size();
    }
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }
    @Override
    public Object[] toArray() {
        return list.toArray();
    }
    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }
    @Override
    public boolean add(Path e) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }
    @Override
    public boolean addAll(Collection<? extends Path> c) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public boolean addAll(int index, Collection<? extends Path> c) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public void clear() {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public Path get(int index) {
        return list.get(index);
    }
    @Override
    public Path set(int index, Path element) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public void add(int index, Path element) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public Path remove(int index) {
        throw new UnsupportedOperationException("Immutable list.");
    }
    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }
    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }
    @Override
    public ListIterator<Path> listIterator() {
        return list.listIterator();
    }
    @Override
    public ListIterator<Path> listIterator(int index) {
        return list.listIterator(index);
    }
    @Override
    public List<Path> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }
}
