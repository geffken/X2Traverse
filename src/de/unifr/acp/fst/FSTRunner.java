package de.unifr.acp.fst;

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
    private Set<State> statusQuo = Collections.<State>emptySet();

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
            String result = output.toString();

            // removes the last ',' from the String
            result = result.substring(0, result.length() - 1);
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
        HashSet<StateAndPermission> set = new HashSet<StateAndPermission>();
        for (State state : statusQuo) {
            set.addAll(step(state, inputChar));
        }
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
    private Set<StateAndPermission> step(final State inputState,
            final String inputChar) {

        // transitive epsilon closure of inputState (ignore output permissions)
        final Set<State> states = transitiveEpsilonClosure(inputState);

        // state set
        Set<State> stts = new HashSet<State>();
        Permission maxPermission = Permission.NONE;
        for (State state : states) {
            Set<State> tmpStates;
            Set<StateAndPermission> sAndPs = state
                    .applyTransitionRelation(inputChar);
            for (StateAndPermission sAndP : sAndPs) {
                stts.add(sAndP.getState());
                maxPermission = Permission.union(maxPermission,
                        sAndP.getPermission());
            }
        }

        /*
         * Creates a temporary HashSet in order not to conflict with the
         * extended transitions (EPSI transitions).
         */
        HashSet<StateAndPermission> statePermStep = new HashSet<StateAndPermission>(
                stts.size());
        if (stts != null) {
            for (State st : stts) {
                if (FSTRunner.debug) {
                    System.out.println(inputChar + "/" + maxPermission + " ( "
                            + inputState.getStateName() + " ==> "
                            + st.getStateName() + " )");
                }
                statePermStep.add(new StateAndPermission(st, maxPermission));
            }
        }

        Set<StateAndPermission> ret = new HashSet<>(statePermStep);
        if (stts != null) {
            transitiveEpsilonExtension(ret, stts);
        }

        return ret;
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
                    .applyTransitionRelation(State.EPSILON);
            assert (stateAndPerms != null);

            // ensure termination (there can be epsilon cycles)
            int oldSize = set.size();
            set.addAll(stateAndPerms);
            if (set.size() == oldSize) {
                return;
            }
            Set<State> newStates = new HashSet<>(stateAndPerms.size());
            for (StateAndPermission sAndP : stateAndPerms) {
                newStates.add(sAndP.getState());
                if (FSTRunner.debug) {
                    System.out.println(State.EPSILON + "/"
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
                .applyTransitionRelation(State.EPSILON);
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

    private Set<State> transitiveEpsilonClosure(State... states) {
        return transitiveEpsilonClosure(Arrays.asList(states));
    }

    // private Set<State> transitiveEpsilonClosure(Collection<State> states) {
    // Set<State> ret = new HashSet<State>(states);
    // for (State state : states) {
    // ret.addAll(state.applyStateTransition(State.EPSILON));
    // }
    // if (ret.size() != states.size()) {
    // return transitiveEpsilonClosure(ret);
    // }
    // return ret;
    // }

    private Set<State> transitiveEpsilonClosure(Collection<State> states) {
        Set<State> result = new HashSet<State>(states);
        Set<State> newStates = new HashSet<State>(result);

        while (!newStates.isEmpty()) {
            Set<State> brandNewStates = new HashSet<State>();
            for (State state : newStates) {
                for (State st : state.applyStateTransition(State.EPSILON)) {
                    if ((!result.contains(st)) && (!newStates.contains(st))) {
                        brandNewStates.add(st);
                    }
                }
            }
            result.addAll(newStates);
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
        statusQuo = Collections.unmodifiableSet(startStates);

        // as long as we do an epsilon closure before and after each step
        // there is no need to do one here
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
        System.out.println(machine.getContracts().trim());
        System.out.println(input);
        System.out.println(result);
        System.out.println("----------------------------");
    }
}
