package de.unifr.acp.fst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohammad Shahabi
 * @author Manuel Geffken
 */
public class State {

    /**
     * The number indicating the name of an {@link State}.
     * Useful when printing the {@link de.unifr.acp.fst.FST}.
     */
    final private int stateName;
    
    /**
     * Mapping of an input string to a set of {@link StateAndPermission}s. The
     * map represents the FST relation Q x I x O x Q.
     */
    private Map<String, Set<StateAndPermission>> transitionRelation = new HashMap<String, Set<StateAndPermission>>();

    /**
     * The constructor.
     * @param i indicating the name of the {@link State}
     */
    public State(final int i) {
        this.stateName = i;
    }
    
    /**
     * Returns the FST's transition relation Q x I x O x Q
     * when we consider Q x I as its domain.
     * @return the transition relation
     */
    public final Map<String, Set<StateAndPermission>> getTransitionRelation() {
        return transitionRelation;
    }

    /**
     * This method adds the specified transition tuple of input string, permission, and state
     * to the transition relation.
     * @param key the input string component (or epsilon) of the tuple to add to the relation
     * @param perm the {@link Permission} component of the tuple to add to the relation.
     * @param state the {@link State} component of the tuple to add to the relation.
     */
    public final void addTransition(final String key, final Permission perm, final State state) {
        if (getTransitionRelation().containsKey(key)) {
            transitionRelation.get(key).add(new StateAndPermission(state, perm));
        } else {
            HashSet<StateAndPermission> stateAndPermissions = new HashSet<>();
            stateAndPermissions.add(new StateAndPermission(state, perm));
            transitionRelation.put(key, stateAndPermissions);
        }
    }
    
    /**
     * Applies the transition relation to the specified input character. Returns the
     * resulting set of state and permission tuples.
     * @param inputChar the input character
     * @return the resulting non-null state and permission set
     */
    public final Set<StateAndPermission> applyTransitionRelation(final String inputChar) {
        Set<StateAndPermission> ret = new HashSet<>();
        
        Set<StateAndPermission> stateAndPerms = getTransitionRelation().get(inputChar);
        if (stateAndPerms != null) {
            for (StateAndPermission stateAndPerm : stateAndPerms) {
                ret.add(stateAndPerm);
            }
        }
        
        // ? matches every input symbol but epsilon
        // identity equality is OK as we only have a singleton epsilon (null)
        if (!inputChar.equals(MetaCharacters.EPSILON)) {
            stateAndPerms = getTransitionRelation().get(
                    MetaCharacters.QUESTION_MARK);
            if (stateAndPerms != null) {
                for (StateAndPermission stateAndPerm : stateAndPerms) {
                    ret.add(stateAndPerm);
                }
            }
        }
        
        return ret;   
    }
    
    /**
     * Returns union of permission components returned by application of the
     * transition relation to the specified input character.
     * Should be called only if one is only interested in the set of
     * possible Permission outputs for a given input char.
     * @param inputChar The input character to which the transition relation is applied.
     * @return the non-null set of output permissions
     */
    public final Set<Permission> applyOutputTransition(final String inputChar) {
        Set<StateAndPermission> stateAndPerms = applyTransitionRelation(inputChar);
        
        assert (stateAndPerms != null);
        Set<Permission> ret = new HashSet<Permission>(stateAndPerms.size());
        
        for (StateAndPermission stateAndPerm : stateAndPerms) {
            ret.add(stateAndPerm.getPermission());
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
    public final Set<State> applyStateTransition(final String inputChar) {
        Set<StateAndPermission> stateAndPerms = applyTransitionRelation(inputChar);
        
        assert (stateAndPerms != null);
        Set<State> ret = new HashSet<State>(stateAndPerms.size());
        
        for (StateAndPermission stateAndPerm : stateAndPerms) {
            ret.add(stateAndPerm.getState());
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
        return stateName;
    }
}
