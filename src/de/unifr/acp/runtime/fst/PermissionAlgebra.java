package de.unifr.acp.runtime.fst;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * A boolean algebra for R/W permissions. The class is basically a wrapper of an {@link EnumSet}
 * implementing the {@link Set} interface.
 * 
 * @author geffken
 * @deprecated {@link de.unifr.acp.runtime.fst.Permission} is a simplified class having the same purpose as this one
 *
 */
public class PermissionAlgebra extends AbstractSet<PermissionAlgebra.Access> {
    
    public static final PermissionAlgebra NONE = new PermissionAlgebra(EnumSet.noneOf(Access.class));
    public static final PermissionAlgebra READ_ONLY = new PermissionAlgebra(EnumSet.of(Access.READ));
    public static final PermissionAlgebra READ_WRITE = new PermissionAlgebra(EnumSet.of(Access.READ, Access.WRITE));
    public static final PermissionAlgebra WRITE_ONLY = new PermissionAlgebra(EnumSet.of(Access.WRITE));
    
    public PermissionAlgebra(EnumSet<PermissionAlgebra.Access> set) {
        this.set = set.clone(); // defensive copy
    }

    private EnumSet<PermissionAlgebra.Access> set;

    @Override
    public boolean add(Access e) {
        return set.add(e);
    }
    
    @Override
    public boolean addAll(Collection<? extends Access> c) {
        return set.addAll(c);
    }
    
    public EnumSet<PermissionAlgebra.Access> asEnumSet() {
        return set.clone();
    }
    
    @Override
    public void clear() {
        set.clear();
    }
    
    @Override
    public PermissionAlgebra clone() {
        try {
            PermissionAlgebra result = (PermissionAlgebra) super.clone();
            result.set = result.set.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
        
    }
    
    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PermissionAlgebra other = (PermissionAlgebra) obj;
        if (set == null) {
            if (other.set != null)
                return false;
        } else if (!set.equals(other.set))
            return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((set == null) ? 0 : set.hashCode());
        return result;
    }
    
    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
    
    @Override
    public Iterator<Access> iterator() {
        return set.iterator();
    }
    
    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public int size() {
        return set.size();
    }
    
    public static enum Access {
        READ, WRITE;
    }

}
