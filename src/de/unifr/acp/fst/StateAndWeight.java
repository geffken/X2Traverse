package de.unifr.acp.fst;

public class StateAndWeight<W> {
    private WAState<W> state;
    private W weight;

    public StateAndWeight(WAState<W> state, W weight) {
        super();
        this.state = state;
        this.weight = weight;
    }

    public int hashCode() {
        int hashFirst = state != null ? state.hashCode() : 0;
        int hashSecond = weight != null ? weight.hashCode() : 0;

        return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
        if (other instanceof StateAndWeight<?>) {
            StateAndWeight<?> otherPair = (StateAndWeight<?>) other;
            return ((this.state == otherPair.state || (this.state != null
                    && otherPair.state != null && this.state
                        .equals(otherPair.state))) && (this.weight == otherPair.weight || (this.weight != null
                    && otherPair.weight != null && this.weight
                        .equals(otherPair.weight))));
        }

        return false;
    }

    public String toString() {
        return "(" + state + ", " + weight + ")";
    }

    public WAState<W> getState() {
        return state;
    }

    public W getWeight() {
        return weight;
    }

    public void setSecond(W second) {
        this.weight = second;
    }
}
