package de.unifr.acp.runtime.nfa;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.unifr.acp.runtime.fst.MetaCharacters;
import de.unifr.acp.runtime.fst.StateAndWeight;

public class NFAState {

    /**
     * The number indicating the name of a state. Useful when printing the
     * automaton.
     */
    final private int name;

    /**
     * Constructor.
     * 
     * @param i
     *            indicating the name of the state.
     */
    public NFAState(int name) {
        super();
        this.name = name;
    }

    /**
     * Mapping of an input string to a set of {@link NFAState}s. The map
     * represents the NFA relation Q x I x Q.
     */
    private Map<String, Set<NFAState>> transitionRelation = new HashMap<>();

    /**
     * String representation for epsilon input.
     */
    public static final String EPSILON = null;

    static final int IS_FINAL = 0x1;
    static final int IS_COACCESSIBLE = 0x2; // can be used to cache coaccessibility

    private int flags = 0x0;

    public boolean isFinal() {
        return (flags & IS_FINAL) > 0;
    }

    public void setFinal(boolean isFinal) {
        this.flags = isFinal ? flags | IS_FINAL : flags & (~IS_FINAL);
    }

    public boolean isCoaccessible() {
        return (flags & IS_COACCESSIBLE) > 0;
    }

    public void setCoaccessible(boolean isCoaccessible) {
        this.flags = isCoaccessible ? flags | IS_COACCESSIBLE : flags
                & (~IS_COACCESSIBLE);
    }

    /**
     * Returns the FST's transition relation Q x I x Q for this state (we
     * consider I as its domain).
     * 
     * @return the transition relation
     */
    public final Map<String, Set<NFAState>> getTransitionRelation() {
        return transitionRelation;
    }

    /**
     * This method adds the specified transition tuple of input string, weight,
     * and state to the transition relation.
     * 
     * @param inputChar
     *            the input character component (or epsilon) of the tuple to add
     *            to the relation
     * @param weight
     *            the weight component of the tuple to add to the relation.
     * @param state
     *            the state component of the tuple to add to the relation.
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
     * Applies the transition relation to the specified input character. Returns
     * the resulting set of state and weight tuples.
     * 
     * @param inputChar
     *            the input character
     * @return the resulting non-null state and weight set (unmodifiable)
     */
    public final Set<NFAState> applyTransitionRelation(final String inputChar) {
        Set<NFAState> ret;
        Map<String, Set<NFAState>> transRelation = getTransitionRelation();
        Set<NFAState> states = transRelation.get(inputChar);

        if (states != null) {
            ret = new HashSet<>(states);
            
            // ? matches every input symbol but epsilon (duplicated for performance)
            if (!inputChar.equals(MetaCharacters.EPSILON)) {
                states = transRelation.get(MetaCharacters.QUESTION_MARK);
                if (states != null) {
                    ret.addAll(states);
                }
            }
        } else {
            ret = Collections.EMPTY_SET;
            
            // ? matches every input symbol but epsilon  (duplicated for performance)
            if (!inputChar.equals(MetaCharacters.EPSILON)) {
                states = transRelation.get(MetaCharacters.QUESTION_MARK);
                if (states != null) {
                    ret = states;
                }
            }
        }

        return Collections.unmodifiableSet(ret);
    }

}
