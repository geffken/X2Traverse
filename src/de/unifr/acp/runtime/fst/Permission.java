package de.unifr.acp.runtime.fst;

import java.util.Collection;

/**
 * This permission enumeration represents a specific enumeration set of
 * {@link Permission.Access} elements. However, instances of this class are
 * immutable. Typical operations are likely to be faster than their
 * {@link EnumSet} counterparts.
 * 
 * @author geffken
 */
public enum Permission {

    /**
     * no access (bottom element)
     */
    NONE,

    /**
     * read-only access.
     */
    READ_ONLY,

    /**
     * write-only access.
     */
    WRITE_ONLY,

    /**
     * read/write access (top element).
     */
    READ_WRITE;

    /**
     * Convenience method. Returns the <code>Permission</code> instance
     * representing the specified ordinal value. The new <code>Permission</code>
     * instance's ordinal value is equal the specified ordinal value.
     * 
     * @param ordinal
     *            the ordinal of the returned <code>Permission</code> value
     * @return the <code>Permission</code> instance
     */
    public static Permission valueOf(int ordinal) {
        try {
            return Permission.values()[ordinal];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown permission value", e);
        }
    }

    public static Permission of(Access a) {
        int ordinal = (1 << a.ordinal());
        return Permission.values()[ordinal];
    }

    public static Permission of(Access a1, Access a2) {
        int ordinal = (1 << a1.ordinal()) | (1 << a2.ordinal());
        return Permission.values()[ordinal];
    }

    public static Permission union(Permission p1, Permission p2) {
//         return Permission.values()[p1.ordinal() | p2.ordinal()];
        switch (p1) {
        case READ_WRITE:
            return p1;
        case WRITE_ONLY:
            if (p2 == Permission.READ_WRITE) 
                return p2;
            else if (p2 == Permission.READ_ONLY)
                return Permission.READ_WRITE;
            else
                return p1;
        case READ_ONLY:
            if (p2 == Permission.READ_WRITE) 
                return p2;
            else if (p2 == Permission.WRITE_ONLY)
                return Permission.READ_WRITE;
            else
                return p1;
        case NONE: return p2;
        default:
            return p2;
        }
    }

    public static Permission union(Collection<Permission> permissions) {
        Permission ret = Permission.NONE;
        for (Permission p : permissions) {
            ret = Permission.union(ret, p);
        }
        return ret;
    }

    public static Permission intersection(Permission p1, Permission p2) {
        return Permission.values()[p1.ordinal() & p2.ordinal()];
    }

    public static Permission intersection(Collection<Permission> permissions) {
        Permission ret = Permission.READ_WRITE;
        for (Permission p : permissions) {
            ret = Permission.intersection(ret, p);
        }
        return ret;
    }

    /* p1.containsAll(p2) */
    public static boolean containsAll(Permission p1, Permission p2) {
        return (p2.ordinal() & ~p1.ordinal()) == 0;
    }

    public boolean containsAll(Permission p) {
        return (p.ordinal() & ~this.ordinal()) == 0;
    }

    public static enum Access {
        READ, WRITE;
    }
}