package de.unifr.acp.runtime.nfa;

/**
 * An immutable object and NFA runner pair. Uses the object's identity
 * in for its <code>equals</code> and <code>hashCode</code> semantics.
 * 
 * @author geffken
 */
public final class ObjectAndNFARunner {
    public final Object obj;
    public final NFARunner runner;

    public ObjectAndNFARunner(Object obj, NFARunner runner) {
        this.obj = obj;
        this.runner = runner;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        // use object's identity hash code
        result = prime * result + ((obj == null) ? 0 : System.identityHashCode(obj));
        result = prime * result + ((runner == null) ? 0 : runner.hashCode());
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
        ObjectAndNFARunner other = (ObjectAndNFARunner) obj;
        if (this.obj == null) {
            if (other.obj != null)
                return false;
        } else if (this.obj != other.obj) // compare objects based on identity
            return false;
        if (runner == null) {
            if (other.runner != null)
                return false;
        } else if (!runner.equals(other.runner))
            return false;
        return true;
    }
    
    
    
}
