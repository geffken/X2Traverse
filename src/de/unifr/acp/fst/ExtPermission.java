package de.unifr.acp.fst;

/**
 * An extended permission lattice with an additional top and bottom element. Can
 * be used as the base set for a semiring S with the operations
 * {@link #join(ExtPermission,ExtPermission)} (addition) and
 * {@link #overwrite(ExtPermission,ExtPermission)} (multiplication) such that S
 * = (<code>ExtPermission</code>, <code>join</code>, <code>overwrite</code>,
 * <code>BOTTOM</code>, <code>TOP</code>).
 * 
 * @see ExtPermission#join(ExtPermission, ExtPermission)
 * @see ExtPermission#overwrite(ExtPermission, ExtPermission)
 * @author geffken
 * 
 */
public enum ExtPermission {
    /**
     * no access (bottom element)
     */
    NONE(Permission.NONE),

    /**
     * read-only access.
     */
    READ_ONLY(Permission.READ_ONLY),

    /**
     * write-only access.
     */
    WRITE_ONLY(Permission.WRITE_ONLY),

    /**
     * read/write access (top element).
     */
    READ_WRITE(Permission.READ_WRITE),

    TOP,

    BOTTOM;

    // an alternative name for the top element
    public final static ExtPermission EPSILON = TOP;

    public Permission perm;

    ExtPermission() {
    }

    ExtPermission(Permission perm) {
        this.perm = perm;
    }

    /**
     * Convenience method. Returns the <code>ExtendedPermission</code> instance
     * representing the specified permission value. The returned
     * <code>ExtendedPermission</code> instance's ordinal value is equal the
     * specified permission's ordinal value.
     * 
     * @param the
     *            <code>Permission</code> whose ordinal value to take into
     *            account
     * @return the <code>ExtendedPermission</code> instance
     */
    public static ExtPermission valueOf(Permission p) {
        try {
            return ExtPermission.values()[p.ordinal()];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown permission value", e);
        }
    }

    public Permission toPermission() {
        switch (this) {
        case TOP:
            return Permission.READ_WRITE;
        case BOTTOM:
            return Permission.NONE;
        default:
            return perm;
        }
    }

    /**
     * Returns the least upper bound of this permission lattice.
     * 
     * @return the least upper bound
     */
    public static ExtPermission join(ExtPermission p1, ExtPermission p2) {
        switch (p1) {
        case TOP:
            return p1; // TOP
        case BOTTOM:
            return p2;
        default:
            switch (p2) {
            case TOP:
                return p2; // TOP
            case BOTTOM:
                return p1;
            default:
                return ExtPermission
                        .valueOf(Permission.union(p1.perm, p2.perm));
            }
        }
    }

    public static ExtPermission overwrite(ExtPermission p1, ExtPermission p2) {
        switch (p1) {
        case TOP:
            return p2;
        case BOTTOM:
            return p1; // BOTTOM
        default:
            switch (p2) {
            case TOP:
                return p1;
            case BOTTOM:
                return p2; // BOTTOM
            default:
                return p2;
            }
        }
    }
}
