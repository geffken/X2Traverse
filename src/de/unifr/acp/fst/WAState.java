package de.unifr.acp.fst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A state of a weighted automaton.
 * 
 * @author geffken
 *
 * @param <W> The type representing the weight.
 */
public class WAState<W> {
    
    /**
     * The number indicating the name of an {@link WAState}.
     * Useful when printing the weighted automaton.
     */
    final private int name;
    
    /**
     * Mapping of an input string to a set of {@link StateAndWeight}s. The
     * map represents the FST relation Q x I x K x Q.
     */
    private Map<String, Set<StateAndWeight<W>>> transitionRelation = new HashMap<>();

    /**
     * String representation for epsilon input in weighted automaton.
     */
    public static final String EPSILON = null;

    /**
     * The constructor.
     * @param i indicating the name of the {@link State}
     */
    public WAState(final int i) {
        this.name = i;
    }
    
    /**
     * Returns the FST's transition relation Q x I x W x Q
     * when we consider Q x I as its domain.
     * @return the transition relation
     */
    public final Map<String, Set<StateAndWeight<W>>> getTransitionRelation() {
        return transitionRelation;
    }
    
    /**
     * This method adds the specified transition tuple of input string, weight, and state
     * to the transition relation.
     * @param inputChar the input character component (or epsilon) of the tuple to add to the relation
     * @param weight the weight component of the tuple to add to the relation.
     * @param state the state component of the tuple to add to the relation.
     */
    public final void addTransition(final String inputChar, final W weight, final WAState<W> state) {
        if (getTransitionRelation().containsKey(inputChar)) {
            transitionRelation.get(inputChar).add(new StateAndWeight<W>(state, weight));
        } else {
            HashSet<StateAndWeight<W>> stateAndWeights = new HashSet<>();
            stateAndWeights.add(new StateAndWeight<W>(state, weight));
            transitionRelation.put(inputChar, stateAndWeights);
        }
    }
    
    /**
     * Applies the transition relation to the specified input character. Returns the
     * resulting set of state and weight tuples.
     * @param inputChar the input character
     * @return the resulting non-null state and weight set
     */
    public final Set<StateAndWeight<W>> applyTransitionRelation(final String inputChar) {
        Set<StateAndWeight<W>> ret = new HashSet<>();
        
        Set<StateAndWeight<W>> stateAndWeights = getTransitionRelation().get(inputChar);
        if (stateAndWeights != null) {
            for (StateAndWeight<W> stateAndWeight : stateAndWeights) {
                ret.add(stateAndWeight);
            }
        }
        
        // ? matches every input symbol but epsilon
        if (!inputChar.equals(MetaCharacters.EPSILON)) {
            stateAndWeights = getTransitionRelation().get(
                    MetaCharacters.QUESTION_MARK);
            if (stateAndWeights != null) {
                for (StateAndWeight<W> stateAndWeight : stateAndWeights) {
                    ret.add(stateAndWeight);
                }
            }
        }
        
        return ret;
    }
    
    /**
     * Returns union of weight components returned by application of the
     * transition relation to the specified input character.
     * Should be called only if one is only interested in the set of
     * possible weight outputs for a given input char.
     * @param inputChar The input character to which the transition relation is applied.
     * @return the non-null set of output weights.
     */
    public final Set<W> applyOutputTransition(final String inputChar) {
        Set<StateAndWeight<W>> stateAndWeights = applyTransitionRelation(inputChar);
        
        assert (stateAndWeights != null);
        Set<W> ret = new HashSet<>(stateAndWeights.size());
        
        for (StateAndWeight<W> stateAndWeight : stateAndWeights) {
            ret.add(stateAndWeight.getWeight());
        }
        
        return ret;
    }

    /**
     * Returns the union of state components returned by application of the
     * transition relation to the specified input character.
     * Should be called only if one is only interested in the set of
     * possible target states for a given input char.
     * @param inputChar The input character to which the transition relation is applied.
     * @return the non-null set of target states
     */
    public final Set<WAState<W>> applyStateTransition(final String inputChar) {
        Set<StateAndWeight<W>> stateAndWeights = applyTransitionRelation(inputChar);
        
        assert (stateAndWeights != null);
        Set<WAState<W>> ret = new HashSet<>(stateAndWeights.size());
        
        for (StateAndWeight<W> stateAndWeight : stateAndWeights) {
            ret.add(stateAndWeight.getState());
        }
        
        return ret;
    }

    /**
     * Returns whether this state's transition relation contains the specified
     * input key.
     * 
     * @param input
     *            The input key that is about to be checked
     * @return <code>true</code> if the transition relation returned by
     *         {@link State#getTransitionRelation()} contains the input Key,
     *         false otherwise
     */
    public final boolean containsTransition(final String input) {
        return getTransitionRelation().containsKey(input);
    }

    /**
     * Returns the state name of this state.
     * @return the state name
     */
    public final int getStateName() {
        return name;
    }

}
