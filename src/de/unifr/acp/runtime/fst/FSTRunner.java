package de.unifr.acp.runtime.fst;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a runner for a non-deterministic machine {@link FST}.
 * The runner can perform a step-wise run. The target automaton being run can be
 * in multiple states at once (backtracking is avoided). The runner does not
 * mutate the underlying FST (states, transitions, etc.) but is only mutable in
 * the set of current states.
 * 
 * @author Mohammad Shahabi
 * @author geffken
 * 
 */
public final class FSTRunner implements Cloneable {

    /**
     * Enables debug printing if true, disables it otherwise.
     * 
     * @see fst.FST#debugPrint()
     */
    public static boolean debug = false;

    /**
     * Unmodifiable set of current states.
     */
    private Set<State> statusQuo = Collections.<State> emptySet();

    /**
     * The {@link FST} this runner is running.
     */
    private final FST machine;

    /**
     * @param fst
     *            the {@link FST} to run with this runner
     */
    public FSTRunner(final FST fst) {
        this.machine = fst;
        reset();
    }

    /**
     * Get this runner's current machine states.
     * 
     * @return HashSet<State>
     */
    public Set<State> getStatusQuo() {
        return statusQuo;
    }

    /**
     * Run the machine from scratch on the specified input.
     * 
     * @param input
     *            the input to run the {@link FST} on.
     * @return The result of the {@link FST} run
     * @see FSTRunner#runFromScratch(String)
     */
    public String runFromScratch(final String input) {

        // Checks whether the input complies with the automaton's alphabet
        if (getMachine().getInputAlphabet().valid(input)) {
            reset();

            // the output of the executed FST
            StringBuilder output = new StringBuilder();

            /*
             * Split the input by '.' and iteratively call step for each member.
             */
            String[] strArr = input.split("\\.");
            for (int i = 0; i < strArr.length; i++) {
                Permission perm = step(strArr[i]);
                output.append(perm);
                output.append(',');
            }

            // removes the last ',' from the String
            output.deleteCharAt(output.length() - 1);
            String result = output.toString();

            printResult(getMachine(), input, result);
            return result;
        } else {
            throw new IllegalArgumentException(
                    "The input contains characters that are not in the FST's alphabet.");
        }
    }

/**
     * Convenience method.
     * 
     * Calls {@link fst.FST#step(inputChar) after a reset.
     * @param inputChar the input character to consume.
     * @return the result of {@link FSTRunner#step(String)}
     */
    public Permission resetAndStep(final String inputChar) {
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
    public Permission step(final String inputChar) {
        if (FSTRunner.debug) {
            System.out.println("Consume " + inputChar + "\n---------------");
        }

        Set<StateAndPermission> set;
        set = step(statusQuo, inputChar); // step multiple states

        Set<State> newStatusQuo = new HashSet<State>(set.size());
        for (StateAndPermission stateAndPerm : set) {
            newStatusQuo.add(stateAndPerm.getState());
        }
        statusQuo = Collections.unmodifiableSet(newStatusQuo);

        Permission perm = getMaxPermission(set);

        if (FSTRunner.debug) {
            System.out.println("The permission is: " + perm);
            System.out.println("_____________________________________________");
        }
        return perm;
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
    private Set<StateAndPermission> step(final State inputState,
            final String inputChar) {
        return step(Collections.singleton(inputState), inputChar);
    }

    /**
     * Adds all the possible state transitions from the {@code inputStates},
     * consuming {@code inputCharacter} to the possible state transitions.
     * 
     * @param inputStates
     *            The set of input {@link State}s in which we are supposed to
     *            find possible transitions
     * @param inputChar
     *            The (non-epsilon) input that has to be consumed on the
     *            generated {@link FST}
     * @return A set of {@link StateAndPermission} which are possible to take
     *         consuming the {@code inputChar}
     */
    private Set<StateAndPermission> step(final Set<State> inputStates,
            final String inputChar) {

        // no transitive epsilon closure of inputState (ignore output
        // permissions)
        // as statusQuo is always epsilon-closed (provided reset is called
        // initially)
        // final Set<State> states = transitiveEpsilonClosure(inputState);

        Set<StateAndPermission> statePermStep = new HashSet<StateAndPermission>();

        // state set
        for (State inputState : inputStates) {
            Set<StateAndPermission> sAndPs = inputState
                    .applyTransitionRelation(inputChar);
            statePermStep.addAll(sAndPs);
        }

        Set<State> stts = new HashSet<State>(statePermStep.size());
        for (StateAndPermission sAndP : statePermStep) {
            stts.add(sAndP.getState());
        }

        if (stts != null) {
            transitiveEpsilonExtension(statePermStep, stts);
        }

        return statePermStep;
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
    private Permission getMaxPermission(final Set<StateAndPermission> set) {
        Permission ret = Permission.NONE;
        for (StateAndPermission stateAndPermission : set) {
            final Permission permission = stateAndPermission.getPermission();
            ret = Permission.union(ret, permission);
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
    private void transitiveEpsilonExtension(final Set<StateAndPermission> set,
            final State state) {
        if (state != null) {
            Set<StateAndPermission> stateAndPerms = state
                    .applyTransitionRelation(MetaCharacters.EPSILON);
            assert (stateAndPerms != null);

            // ensure termination (there can be epsilon cycles)
            if (!set.addAll(stateAndPerms)) {
                return;
            }
            Set<State> newStates = new HashSet<>(stateAndPerms.size());
            for (StateAndPermission sAndP : stateAndPerms) {
                if (!set.contains(sAndP))
                    newStates.add(sAndP.getState());
                if (FSTRunner.debug) {
                    System.out.println(MetaCharacters.EPSILON + "/"
                            + sAndP.getPermission() + " ( "
                            + state.getStateName() + " ==> "
                            + sAndP.getState().getStateName() + " )");
                }
            }
            transitiveEpsilonExtension(set, newStates);
        }
    }

    private void transitiveEpsilonExtension(final Set<StateAndPermission> set,
            final Set<State> states) {
        if (states != null) {
            for (State state : states) {
                transitiveEpsilonExtension(set, state);
            }
        }
    }

    /**
     * @deprecated
     */
    private final Set<StateAndPermission> applyEpsilon(final State state) {
        Set<StateAndPermission> stateAndPerms = state
                .applyTransitionRelation(MetaCharacters.EPSILON);
        assert stateAndPerms != null;
        return stateAndPerms;
    }

    /**
     * @deprecated
     */
    private final Set<StateAndPermission> applyEpsilon(final Set<State> states) {
        Set<StateAndPermission> ret = new HashSet<StateAndPermission>();
        for (State state : states) {
            ret.addAll(applyEpsilon(state));
        }
        return ret;
    }

    private Set<State> transitiveEpsilonClosure(State state) {
        return transitiveEpsilonClosure(Collections.singletonList(state));
    }

    private Set<State> transitiveEpsilonClosure(Collection<State> states) {
        Set<State> result = new HashSet<>(states);
        Set<State> newStates = result;

        while (!newStates.isEmpty()) {
            Set<State> brandNewStates = new HashSet<State>();
            for (State state : newStates) {
                for (State st : state
                        .applyStateTransition(MetaCharacters.EPSILON)) {
                    if ((!result.contains(st))/* && (!newStates.contains(st)) */) {
                        brandNewStates.add(st);
                    }
                }
            }
            result.addAll(brandNewStates);
            newStates = brandNewStates;
        }
        return result;
    }

    /**
     * Moves to the beginning of this runner's {@link FST}.
     */
    public void reset() {
        Set<State> startStates = new HashSet<State>();
        startStates.add(getMachine().getStartState());

        // transitive epsilon closure of inputState (ignore output permissions)
        startStates.addAll(transitiveEpsilonClosure(getMachine()
                .getStartState()));

        statusQuo = Collections.unmodifiableSet(startStates);
    }

    @Override
    public FSTRunner clone() throws CloneNotSupportedException {
        FSTRunner clone = (FSTRunner) super.clone();

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
        FSTRunner other = (FSTRunner) obj;
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

    public FST getMachine() {
        return machine;
    }

    /**
     * Prints the end result in the terminal.
     * 
     * @param machine
     *            {@link FST}
     * @param input
     *            inputString which has been run over the generated {@link FST}
     * @param result
     *            output of the run
     */
    private static void printResult(final FST machine, final String input,
            final String result) {
        System.out.println("-----------FSTRun-----------");
        System.out.println(machine.getContract().trim());
        System.out.println(input);
        System.out.println(result);
        System.out.println("----------------------------");
    }
}
