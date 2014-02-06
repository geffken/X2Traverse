package de.unifr.acp.nfa;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import de.unifr.acp.fst.ExtPermission;
import de.unifr.acp.fst.FST;
import de.unifr.acp.fst.FSTRunner;
import de.unifr.acp.fst.MetaCharacters;
import de.unifr.acp.fst.State;
import de.unifr.acp.fst.WAState;

public class NFARunner implements Cloneable {

    /**
     * Enables debug printing if true, disables it otherwise.
     * 
     * @see NFA#debugPrint()
     */
    public static boolean debug = false;

    /**
     * Unmodifiable set of current states.
     */
    private Set<NFAState> statusQuo = Collections.<NFAState> emptySet();

    /**
     * The {@link NFA} this runner is running.
     */
    private final NFA machine;

    /**
     * @param nfa
     *            the {@link NFA} to run with this runner
     */
    public NFARunner(final NFA nfa) {
        this.machine = nfa;
        reset();
    }

    /**
     * Get this runner's current machine states.
     * 
     * @return the current states
     */
    public Set<NFAState> getStatusQuo() {
        return statusQuo;
    }

    /**
     * Returns true if it exist a state in set of current states that is
     * coaccessible.
     * 
     * @return true if one or more current states are coaccessible, false
     *         otherwise.
     */
    public boolean isCoaccessible() {
        Set<NFAState> reachSet = new HashSet<>(statusQuo);
        transitiveReachabilityClosure(reachSet);
        return isFinal(reachSet);
    }

    /**
     * A set of states is final if at least one state is final.
     * 
     * @param states
     *            the set of states to check
     * @return true if one or more states are final, false otherwise
     */
    public static boolean isFinal(Collection<NFAState> states) {
        for (NFAState state : states) {
            if (state.isFinal()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is the set of current states of this runner final?
     * 
     * @return true if one or more current states are final, false otherwise
     */
    public boolean isFinal() {
        return isFinal(statusQuo);
    }

    /**
     * Run the machine from scratch on the specified input.
     * 
     * @param input
     *            the input to run the {@link FST} on.
     * @return this runner
     * @see FSTRunner#runFromScratch(String)
     */
    public NFARunner runFromScratch(final String input) {
        reset();

        /*
         * Split the input by '.' and iteratively call step for each member.
         */
        String[] strArr = input.split("\\.");
        for (int i = 0; i < strArr.length; i++) {
            step(strArr[i]);
        }
        return this;
    }

    /**
     * Take one step from the current state set updates the current state set to
     * the states reachable via the input character.
     * 
     * @param inputChar
     *            The input char to consume
     * @return true if one of the reached
     */
    public boolean step(final String inputChar) {
        if (NFARunner.debug) {
            System.out.println("Consume " + inputChar + "\n---------------");
        }

        // step from multiple states
        boolean isFinal = step(statusQuo, inputChar);

        if (FSTRunner.debug) {
            System.out.println("A target state is final: " + isFinal);
            System.out.println("_____________________________________________");
        }
        return isFinal;
    }

    /**
     * Returns all the states reachable from the input states, consuming the
     * input character.
     * 
     * @param inputStates
     *            The set of input {@link State}s in which we are supposed to
     *            find possible transitions
     * @param inputChar
     *            The (non-epsilon) input that has to be consumed on the
     *            generated {@link FST}
     * @return true if the NFA accepts the input char from the current states,
     *         false otherwise
     */
    private boolean step(final Set<NFAState> inputStates, final String inputChar) {

        // no transitive epsilon closure of inputState (ignore output
        // permissions)
        // as statusQuo is always epsilon-closed (provided reset is called
        // initially)

        Set<NFAState> result = new HashSet<NFAState>();

        // state set
        for (NFAState inputState : inputStates) {
            Set<NFAState> states = inputState
                    .applyTransitionRelation(inputChar);
            result.addAll(states);
        }

        transitiveEpsilonClosure(result);

        statusQuo = Collections.unmodifiableSet(result);

        return isFinal(statusQuo);
    }

    /**
     * Moves to the beginning of this runner's {@link FST}.
     */
    public void reset() {
        Set<NFAState> startStates = new HashSet<>();
        startStates.add(getMachine().getStartState());

        // transitive epsilon closure of inputState (ignore output permissions)
        startStates.addAll(transitiveEpsilonClosure(getMachine()
                .getStartState()));

        statusQuo = Collections.unmodifiableSet(startStates);
    }

    /**
     * Reachability-closes the specified (mutable) set of states.
     * 
     * @param states
     *            the set of states
     */
    private void transitiveReachabilityClosure(Collection<NFAState> states) {
        Collection<NFAState> newStates = states;

        while (true) {
            Collection<NFAState> brandNewStates = new HashSet<>();
            for (NFAState state : newStates) {
                for (Set<NFAState> reachSet : state.getTransitionRelation()
                        .values()) {
                    brandNewStates.addAll(reachSet);
                }
            }
            if (!states.addAll(brandNewStates)) {
                break;
            }
            newStates = brandNewStates;
        }
    }

    private Set<NFAState> transitiveEpsilonClosure(NFAState state) {
        Set<NFAState> states = new HashSet<>();
        states.add(state);
        transitiveEpsilonClosure(states);
        return states;
    }

    /**
     * Epsilon-closes the specified (mutable) set of states.
     * 
     * @param states
     *            the set of states to be epsilon-closed.
     */
    private void transitiveEpsilonClosure(Set<NFAState> states) {
        Set<NFAState> newStates = states;

        while (true) {
            Set<NFAState> brandNewStates = new HashSet<>();
            for (NFAState state : newStates) {
                for (NFAState st : state
                        .applyTransitionRelation(MetaCharacters.EPSILON)) {
                    brandNewStates.add(st);
                }
            }
            if (!states.addAll(brandNewStates)) {
                break;
            }
            newStates = brandNewStates;
        }
    }

    @Override
    public NFARunner clone() throws CloneNotSupportedException {
        NFARunner clone = (NFARunner) super.clone();

        // Clone can be avoided if we never modify statusQuo but replace it.
        // Does this invariant hold? Yes!
        // clone.statusQuo = Collections.unmodifiableSet(statusQuo);
        return clone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((machine == null) ? 0 : machine.hashCode());
        result = prime * result
                + ((statusQuo == null) ? 0 : statusQuo.hashCode());
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
        NFARunner other = (NFARunner) obj;
        if (machine == null) {
            if (other.machine != null)
                return false;
        } else if (!machine.equals(other.machine))
            return false;
        if (statusQuo == null) {
            if (other.statusQuo != null)
                return false;
        } else if (!statusQuo.equals(other.statusQuo))
            return false;
        return true;
    }

    public NFA getMachine() {
        return machine;
    }

    /**
     * Prints the end result in the terminal.
     * 
     * @param automaton
     *            the NFA
     * @param input
     *            input string which has been run over the generated automaton
     * @param result
     *            output of the run
     */
    private static void printResult(final NFA automaton, final String input,
            final String result) {
        System.out.println("-----------APCRun-----------");
        // System.out.println(automaton.getContract().trim());
        System.out.println(input);
        System.out.println(result);
        System.out.println("----------------------------");
    }
}
