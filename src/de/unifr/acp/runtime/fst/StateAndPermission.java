package de.unifr.acp.runtime.fst;

/**
 * An immutable state and permission tuple.
 * 
 * @author Mohammad Shahabi
 * @author geffken
 */
class StateAndPermission {
    /**
     * {@link State}.
     */
    private final State state;

    /**
     * {@link Permission}.
     */
    private final Permission permission;

    /**
     * @param state
     *            {@link State}
     * @param perm
     *            {@link Permission}
     */
    public StateAndPermission(final State state, final Permission perm) {
        this.state = state;
        this.permission = perm;
    }

    /**
     * @return {@link State}
     */
    public State getState() {
        return state;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((permission == null) ? 0 : permission.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StateAndPermission other = (StateAndPermission) obj;
        if (permission != other.permission)
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }

    /**
     * @return {@link Permission}
     */
    public Permission getPermission() {
        return permission;
    }
}
