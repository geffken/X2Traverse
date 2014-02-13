package de.unifr.acp.runtime.fst;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a weighted automaton over the specified semiring. The
 * automaton's input alphabet is the set of all strings. Supports epsilon
 * transitions in the input component of the transitions relation.
 * 
 * @param <W>
 *            the type of semiring
 * 
 * @author geffken
 */
public class WeightedAutomaton<W> {

    /**
     * To see the generated ({@link FST}).
     * 
     * @see FST#debugPrint()
     */
    public static boolean debug = false;

    /**
     * Indicates the start state of the {@link FST}.<br>
     * In order to run the {@link FST} correctly, the start state has to be
     * given.
     */
    protected final WAState<W> startState;

    protected Set<WAState<W>> finalStates = new HashSet<>();

    /**
     * Indicates the the next fresh number of a State.
     */
    private static int freshStateNum = 0;

    /**
     * States of this state machine indexed by state name. Used for debug
     * printing this {@link FST}. Redundant information if only reachable states
     * need to be printed.
     */
    // private Map<Integer, WAState<W>> statesByName = new HashMap<>();

    /**
     * Create a new weighted automaton with a start state.
     * 
     */
    public WeightedAutomaton() {

        // generate start state (0 by convention)
        this.startState = genFreshState();

        // set standard ASCII-based input alphabet
        // setInputAlphabet(new Alphabet());
    }

    protected WAState<W> genFreshState() {
        WAState<W> state = new WAState<>(freshStateNum++);
        return state;
    }

    protected Map<Integer, WAState<W>> getStatesByName() {
        Map<Integer, WAState<W>> statesByName = new HashMap<>();
        int oldSize = 0;
        statesByName.put(this.startState.getStateName(), this.startState);
        Set<WAState<W>> newStates = Collections.singleton(this.startState);
        Set<WAState<W>> postStates = new HashSet<>();
        while (oldSize < statesByName.size()) {
            oldSize = statesByName.size();
            newStates = postStates;
            postStates = new HashSet<>();
            for (WAState<W> state : newStates) {
                for (Set<StateAndWeight<W>> stateAndWeights : state
                        .getTransitionRelation().values()) {
                    for (StateAndWeight<W> stateAndWeight : stateAndWeights) {
                        postStates.add(stateAndWeight.getState());
                    }
                }

            }
            for (WAState<W> postState : postStates) {
                statesByName.put(postState.getStateName(), postState);
            }
        }
        return statesByName;
    }

    public WAState<W> getStartState() {
        return startState;
    }

    public Set<WAState<W>> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(Set<WAState<W>> states) {
        this.finalStates = states;
    }
}
