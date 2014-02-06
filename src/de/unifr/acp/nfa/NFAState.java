package de.unifr.acp.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.unifr.acp.fst.MetaCharacters;
import de.unifr.acp.fst.StateAndWeight;

public class NFAState {

    /**
     * The number indicating the name of a state.
     * Useful when printing the automaton.
     */
    final private int name;
    
    /**
     * Constructor.
     * @param i indicating the name of the state.
     */
    public NFAState(int name) {
        super();
        this.name = name;
    }
    
    /**
     * Mapping of an input string to a set of {@link NFAState}s. The
     * map represents the NFA relation Q x I x Q.
     */
    private Map<String, Set<NFAState>> transitionRelation = new HashMap<>();
    
    /**
     * String representation for epsilon input.
     */
    public static final String EPSILON = null;
    
    private boolean isFinal = false;
    
    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Returns the FST's transition relation Q x I x Q
     * for this state (we consider I as its domain).
     * @return the transition relation
     */
    public final Map<String, Set<NFAState>> getTransitionRelation() {
        return transitionRelation;
    }
    
    /**
     * This method adds the specified transition tuple of input string, weight, and state
     * to the transition relation.
     * @param inputChar the input character component (or epsilon) of the tuple to add to the relation
     * @param weight the weight component of the tuple to add to the relation.
     * @param state the state component of the tuple to add to the relation.
     */
    public final void addTransition(final String inputChar, final NFAState state) {
        if (getTransitionRelation().containsKey(inputChar)) {
            transitionRelation.get(inputChar).add(state);
        } else {
            HashSet<NFAState> states = new HashSet<>();
            states.add(state);
            transitionRelation.put(inputChar, states);
        }
    }
    
    /**
     * Applies the transition relation to the specified input character. Returns the
     * resulting set of state and weight tuples.
     * @param inputChar the input character
     * @return the resulting non-null state and weight set
     */
    public final Set<NFAState> applyTransitionRelation(final String inputChar) {
        Set<NFAState> ret;
        
        Set<NFAState> states = getTransitionRelation().get(inputChar);
        
        if (states != null) {
            ret = new HashSet<>(states);
        } else {
            ret = new HashSet<>();
        }
        
        // ? matches every input symbol but epsilon
        if (!inputChar.equals(MetaCharacters.EPSILON)) {
            states = getTransitionRelation().get(
                    MetaCharacters.QUESTION_MARK);
            if (states != null) {
                ret.addAll(states);
            }
        }
        
        return ret;
    }
    
    
    
    

}
