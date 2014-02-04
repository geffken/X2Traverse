package de.unifr.acp.fst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * This class represents a runner for a non-deterministic permission-weighted
 * automaton. The runner can perform a step-wise run. The target automaton being
 * run can be in multiple states at once (backtracking is avoided). The runner
 * does not mutate the underlying automaton (states, transitions, etc.) but is
 * only mutable in the set of current states.
 * 
 * @author geffken
 * 
 */

public final class APCRunner implements Cloneable {

    /**
     * Enables debug printing for this runner if true, disables it otherwise.
     */
    public static boolean debug = false;

    /**
     * Unmodifiable set of current states.
     */
    private Set<WAState<ExtPermission>> statusQuo = Collections.emptySet();

    /**
     * The {@link APCAutomaton} this runner is running.
     */
    private final APCAutomaton machine;

    /**
     * @param automaton
     *            the {@link APCAutomaton} to run with this runner
     */
    public APCRunner(final APCAutomaton automaton) {
        this.machine = automaton;
        reset();
    }

    /**
     * Get this runner's current machine states.
     * 
     * @return the set of states the runner is currently in
     */
    public Set<WAState<ExtPermission>> getStatusQuo() {
        return statusQuo;
    }

    /**
     * Run the machine from scratch on the specified input. String-based
     * convenience method.
     * 
     * @param input
     *            the input to run the {@link FST} on.
     * @return The result of the {@link FST} run
     * @see FSTRunner#runFromScratch(String)
     */
    public ExtPermission runFromScratch(final String input) {
        reset();

        /*
         * Split the input by '.' and iteratively call step for each member.
         */
        String[] strArr = input.split("\\.");
        ExtPermission result = ExtPermission.EPSILON;
        for (int i = 0; i < strArr.length; i++) {
            result = ExtPermission.overwrite(result, step(strArr[i]));
        }

        printResult(getMachine(), input, result.toString());
        return result;
    }

    /**
     * Convenience method. Calls {@link fst.FST#step(inputChar)} after a reset.
     * 
     * @param inputChar
     *            the input character to consume.
     * @return the result of {@link FSTRunner#step(String)}
     */
    public ExtPermission resetAndStep(final String inputChar) {
        reset();
        return step(inputChar);
    }

    /**
     * Take one step from the current state set based on a single input and adds
     * all the possible state transitions.
     * 
     * @param inputChar
     *            The input char to consume
     * @return maximum {@link Permission} in the {@link FSTRunner#statusQuo}
     *         transitions
     */
    public ExtPermission step(final String inputChar) {
        if (FSTRunner.debug) {
            System.out.println("Consume " + inputChar + "\n---------------");
        }

        ExtPermission maxPerm = step(statusQuo, inputChar); // step multiple
                                                            // states

        if (FSTRunner.debug) {
            System.out.println("The permission is: " + maxPerm);
            System.out.println("_____________________________________________");
        }
        return maxPerm;
    }

    /**
     * Adds all the possible state transitions from the {@code inputState},
     * consuming {@code inputCharacter} to the possible state transitions.
     * Convenience method. Significantly slower for a set of states. Use
     * overloaded step method instead.
     * 
     * @param inputState
     *            The {@link State} in which we are supposed to find possible
     *            transitions
     * @param inputChar
     *            The (non-epsilon) input that has to be consumed on the
     *            generated {@link FST}
     * @return A set of {@link StateAndPermission} which are possible to take
     *         consuming the {@code inputCharacter}
     */
    @Deprecated
    private ExtPermission step(final WAState<ExtPermission> inputState,
            final String inputChar) {
        return step(Collections.singleton(inputState), inputChar);
    }

    /**
     * Performs one step from the specified states and epsilon-closes the
     * resulting set of states. Updates current set of states. Returns the join
     * of the permissions resulting from all possible steps.
     * 
     * @param inputStates
     *            The set of input {@link State}s in which we are supposed to
     *            find possible transitions
     * @param inputChar
     *            The (non-epsilon) input that has to be consumed on the
     *            generated {@link FST}
     * @return The join of the resulting permissions in this step
     */
    private ExtPermission step(final Set<WAState<ExtPermission>> inputStates,
            final String inputChar) {

        // no transitive epsilon closure of inputState (ignore output
        // permissions)
        // as statusQuo is always epsilon-closed (provided reset is called
        // initially)
        // final Set<State> states = transitiveEpsilonClosure(inputState);

        Set<StateAndWeight<ExtPermission>> statePermStep = new HashSet<>();

        // state set
        for (WAState<ExtPermission> inputState : inputStates) {
            Set<StateAndWeight<ExtPermission>> sAndPs = inputState
                    .applyTransitionRelation(inputChar);
            statePermStep.addAll(sAndPs);
        }

        Set<WAState<ExtPermission>> stts = new HashSet<>(statePermStep.size());
        for (StateAndWeight<ExtPermission> sAndP : statePermStep) {
            stts.add(sAndP.getState());
        }

        // We assume that all epsilon transitions only output epsilon. Thus,
        // the overwrite operatation has no effect and we therefore do not
        // consider permissions.
        transitiveEpsilonClosure(stts);

        statusQuo = Collections.unmodifiableSet(stts);

        ExtPermission maxPerm = getMaxPermission(statePermStep);

        return maxPerm;
    }

    /**
     * Gets the maximum {@link Permission} from the available state transitions.
     * 
     * @param set
     *            Set of {@link StateAndPermission}s to loop for the
     *            {@link Permission}s
     * @return The maximum {@link Permission} at each transition (remember we
     *         may have several current states - {@link FSTRunner#statusQuo})
     */
    private ExtPermission getMaxPermission(
            final Set<StateAndWeight<ExtPermission>> set) {
        // formally BOTTOM should be used are
        ExtPermission ret = ExtPermission.valueOf(Permission.NONE);
        for (StateAndWeight<ExtPermission> stateAndWeight : set) {
            final ExtPermission permission = stateAndWeight.getWeight();
            ret = ExtPermission.join(ret, permission);
        }
        return ret;
    }

    /**
     * Recursively sums up and adds all via {@link State#EPSILON} transitions
     * transitively reachable states and permissions to the in/out parameter
     * <code>set</code>. The added set excludes the initial state and thus is
     * not the epsilon closure.
     * 
     * @param set
     *            In/out parameter. The set of current
     *            {@link StateAndPermission}s where each state is associated
     *            with the last transition's output permission
     * @param state
     *            The {@link State} that is to be checked for
     *            {@link State#EPSILON} transitions
     */
    @Deprecated
    private void transitiveEpsilonExtension(
            final Set<StateAndWeight<ExtPermission>> set,
            final WAState<ExtPermission> state) {
        if (state != null) {
            Set<StateAndWeight<ExtPermission>> stateAndPerms = state
                    .applyTransitionRelation(MetaCharacters.EPSILON);
            assert (stateAndPerms != null);

            // ensure termination (there can be epsilon cycles)
            if (!set.addAll(stateAndPerms)) {
                return;
            }
            Set<WAState<ExtPermission>> newStates = new HashSet<>(
                    stateAndPerms.size());
            for (StateAndWeight<ExtPermission> sAndP : stateAndPerms) {
                if (!set.contains(sAndP))
                    newStates.add(sAndP.getState());
                if (FSTRunner.debug) {
                    System.out.println(MetaCharacters.EPSILON + "/"
                            + sAndP.getWeight() + " ( " + state.getStateName()
                            + " ==> " + sAndP.getState().getStateName() + " )");
                }
            }
            transitiveEpsilonExtension(set, newStates);
        }
    }

    @Deprecated
    private void transitiveEpsilonExtension(
            final Set<StateAndWeight<ExtPermission>> set,
            final Set<WAState<ExtPermission>> states) {
        if (states != null) {
            for (WAState<ExtPermission> state : states) {
                transitiveEpsilonExtension(set, state);
            }
        }
    }

    @Deprecated
    private final Set<StateAndWeight<ExtPermission>> applyEpsilon(
            final WAState<ExtPermission> state) {
        return applyEpsilon(Collections.singleton(state));
    }

    @Deprecated
    private final Set<StateAndWeight<ExtPermission>> applyEpsilon(
            final Set<WAState<ExtPermission>> states) {
        Set<StateAndWeight<ExtPermission>> ret = new HashSet<>();
        for (WAState<ExtPermission> state : states) {
            Set<StateAndWeight<ExtPermission>> stateAndPerms = state
                    .applyTransitionRelation(MetaCharacters.EPSILON);
            assert stateAndPerms != null;
            ret.addAll(stateAndPerms);
        }
        return ret;
    }

    private Set<WAState<ExtPermission>> transitiveEpsilonClosure(
            WAState<ExtPermission> state) {
        Set<WAState<ExtPermission>> states = new HashSet<>();
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
    private void transitiveEpsilonClosure(Set<WAState<ExtPermission>> states) {
        Set<WAState<ExtPermission>> newStates = states;

        while (!newStates.isEmpty()) {
            Set<WAState<ExtPermission>> brandNewStates = new HashSet<>();
            for (WAState<ExtPermission> state : newStates) {
                for (WAState<ExtPermission> st : state
                        .applyStateTransition(MetaCharacters.EPSILON)) {
                    brandNewStates.add(st);
                }
            }
            states.addAll(brandNewStates);
            newStates = brandNewStates;
        }
    }

    /**
     * Moves to the beginning of this runner's {@link FST}.
     */
    public void reset() {
        Set<WAState<ExtPermission>> startStates = new HashSet<>();
        startStates.add(getMachine().getStartState());
        // statusQuo = Collections.unmodifiableSet(startStates);

        // transitive epsilon closure of inputState (ignore output permissions)
        startStates.addAll(transitiveEpsilonClosure(getMachine()
                .getStartState()));

        statusQuo = Collections.unmodifiableSet(startStates);
    }

    @Override
    public APCRunner clone() throws CloneNotSupportedException {
        APCRunner clone = (APCRunner) super.clone();

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
        APCRunner other = (APCRunner) obj;
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

    public APCAutomaton getMachine() {
        return machine;
    }

    /**
     * Prints the end result in the terminal.
     * 
     * @param automaton
     *            {@link APCAutomaton}
     * @param input
     *            input string which has been run over the generated automaton
     * @param result
     *            output of the run
     */
    private static void printResult(final APCAutomaton automaton,
            final String input, final String result) {
        System.out.println("-----------FSTRun-----------");
        System.out.println(automaton.getContract().trim());
        System.out.println(input);
        System.out.println(result);
        System.out.println("----------------------------");
    }

}
