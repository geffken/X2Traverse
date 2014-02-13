package de.unifr.acp.runtime.fst;

/**
 * An immutable object and APC automaton runner pair. Uses the object's identity
 * in for its <code>equals</code> and <code>hashCode</code> semantics.
 * 
 * @author geffken
 */
public final class ObjectAndAPCRunner {
    public final Object obj;
    public final APCRunner runner;

    public ObjectAndAPCRunner(Object obj, APCRunner runner) {
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
        ObjectAndAPCRunner other = (ObjectAndAPCRunner) obj;
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
