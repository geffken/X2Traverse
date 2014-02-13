package de.unifr.acp.runtime.fst;

import static de.unifr.acp.runtime.fst.MetaCharacters.QUESTION_MARK;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.unifr.acp.runtime.contract.Concat;
import de.unifr.acp.runtime.contract.Identifier;
import de.unifr.acp.runtime.contract.Or;
import de.unifr.acp.runtime.contract.Path;
import de.unifr.acp.runtime.contract.Plus;
import de.unifr.acp.runtime.contract.QMark;
import de.unifr.acp.runtime.contract.QMarkLit;
import de.unifr.acp.runtime.contract.Star;
import de.unifr.acp.runtime.contract.SuffixOp;
import de.unifr.acp.runtime.parser.MyParser;
import de.unifr.acp.runtime.parser.MyParser.yyException;
import de.unifr.acp.runtime.parser.MyScanner;

/**
 * This class represents a finite state transducer. The automaton's input
 * alphabet is the set of all strings. Supports epsilon transitions in the input
 * component of the transitions relation. Currently no support for epsilons in
 * the output component of the transition relation. Generation from contract
 * avoids epsilon cycles. No support for final states (all states are implicitly
 * final).
 * 
 * @author geffken
 * @author Mohammad Shahabi
 */
public class FST {

    /**
     * To see the generated ({@link FST}).
     * 
     * @see FST#debugPrint()
     */
    public static boolean debug = false;

    /**
     * The allowed {@link Alphabet}.
     */
    private Alphabet inputAlphabet;

    /**
     * States of this state machine indexed by state name. Used for debug
     * printing this {@link FST}. Redundant information if only reachable states
     * need to be printed.
     */
    private Map<Integer, State> statesByName = new HashMap<Integer, State>();

    /**
     * The contract that this {@link FST} was made out of.
     */
    final private String contract;

    /**
     * Indicates the start state of the {@link FST}.<br>
     * In order to run the FST correctly, the start state has to be
     * given.
     */
    final private State startState;

    /**
     * Indicates the the next fresh number of a State.
     */
    private int freshStateNum = 0;

    /**
     * Create a new machine representation for the specified contracts.
     * 
     * @param ctrct
     *            the contract to generate a machine representation for
     */
    public FST(final String ctrct) {

        // generate start state (by convention has number 0 to simplify
        // debugging)
        State startState = addFreshState();
        this.startState = startState;

        // set standard ASCII-based input alphabet
        setInputAlphabet(new Alphabet());
        this.contract = ctrct;

        generate(ctrct);
    }

    /**
     * @return {@link FST#inputAlphabet}
     */
    public final Alphabet getInputAlphabet() {
        return inputAlphabet;
    }

    /**
     * @param input
     *            to set the {@link FST} an inputAlphabet
     */
    public final void setInputAlphabet(final Alphabet input) {
        this.inputAlphabet = input;
    }

    /**
     * @return {@link FST#startState}
     */
    public final State getStartState() {
        return startState;
    }

    /**
     * @return {@link FST#contract}
     */
    public String getContract() {
        return contract;
    }

    /**
     * Generates one FST for all path expressions in the specified composite
     * contract.
     * 
     * @param contract
     *            the composite contract
     */
    private void generate(final String contract) {
        try {
            // parse contract
            Path path = (Path) new MyParser().yyparse(new MyScanner(
                    new StringReader(contract)));

            // recursively generate this FST (interpret contract as
            // read-prefix-closed r/w contract)
            recursivePut(path, Permission.READ_WRITE);

            this.debugPrint();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (yyException e) {
            e.printStackTrace();
        }
    }

    private State addFreshState() {
        State state = new State(freshStateNum++);
        statesByName.put(state.getStateName(), state);
        return state;
    }

    /**
     * Convenience method that calls
     * <code>recursivePut(path, permission, addFreshState())</code>.
     * 
     * @param path
     *            the abstract {@link Path} to generate machine states for.
     * @param permission
     *            the {@link Permission} to use for the paths matching the
     *            abstract path
     */
    private void recursivePut(final Path path, final Permission permission) {

        // generate a final state first
        State end = addFreshState();

        recursivePut(path, permission, startState, end);
    }

    /**
     * Recursively generates transitions and states as required for the
     * specified path in this machine. The given path is interpreted such that
     * locations represented by concrete paths matching the abstract path have
     * the specified permission and locations matching proper prefixes of
     * matching paths carry permission read-only.
     * 
     * @param path
     *            the abstract {@link Path} to generate machine states for.
     * @param permission
     *            the {@link Permission} to use for the paths matching the
     *            abstract path
     * @param start
     *            the start state of the path to generate
     * @param the
     *            final state of the path to generate
     */
    private void recursivePut(final Path path, final Permission permission,
            State start, State end) {
        /*
         * Pattern matching on path object.
         */

        // base cases
        if (path instanceof QMarkLit) {
            start.addTransition(QUESTION_MARK, permission, end);
        } else if (path instanceof Identifier) {
            start.addTransition(((Identifier) path).getName(), permission, end);

            // recursive cases
        } else if (path instanceof Concat) {
            Concat concat = (Concat) path;

            ListIterator<Path> it = concat.listIterator(concat.size());
            Permission currentPerm = permission;
            State nextState = end;
            State previousState;
            while (it.hasPrevious()) {
                Path element = it.previous();
                if (it.hasPrevious()) {
                    previousState = addFreshState();
                    recursivePut(element, currentPerm, previousState, nextState);
                    nextState = previousState;
                } else {
                    previousState = start;
                    recursivePut(element, currentPerm, start, nextState);
                }

                if (!element.isNullable()) {
                    currentPerm = Permission.READ_ONLY;
                }
            }
        } else if (path instanceof Or) {
            for (Path element : ((Or) path)) {
                State startOfSingle = addFreshState();

                // TODO: epsilon/epsilon transition needed
                start.addTransition(MetaCharacters.EPSILON, Permission.NONE,
                        startOfSingle);
                recursivePut(element, permission, startOfSingle, end);
            }
        } else if (path instanceof SuffixOp) {
            SuffixOp operand = (SuffixOp) path;
            // this code avoids epsilon (input) cycles altogether
            if (path instanceof Star) {
                // self loop (repetitive operator)
                // invariant: only ever loop on start states
                recursivePut(operand.getPath(), permission, start, start);

                // TODO: epsilon/epsilon transition needed
                // skip edge
                start.addTransition(MetaCharacters.EPSILON, Permission.NONE,
                        end);
            } else if (path instanceof QMark) {
                recursivePut(operand.getPath(), permission, start, end);

                // TODO: epsilon/epsilon transition needed
                // skip edge
                start.addTransition(MetaCharacters.EPSILON, Permission.NONE,
                        end);
            } else if (path instanceof Plus) {
                Path inner = operand.getPath();
                // on the fly AST transformation from path+ to path.path*
                Concat concat = new Concat(inner, new Star(inner));
                recursivePut(concat, permission, start, end);
            }

        } else {
            throw new IllegalArgumentException("Unknown path type");
        }
    }

    /**
     * Prints this {@link FST} if {@link FST#debug} is <code>true</code>.
     */
    public void debugPrint() {
        if (debug) {
            System.out
                    .println("=============================================================================================================");
            System.out
                    .println("=============================================================================================================");

            /*
             * Orders mapping by number of state.
             */
            ArrayList<Map.Entry<Integer, State>> stateArrList = new ArrayList<Entry<Integer, State>>(
                    statesByName.entrySet());
            Collections.sort(stateArrList,
                    new Comparator<Map.Entry<Integer, State>>() {
                        public final int compare(
                                final Map.Entry<Integer, State> o1,
                                final Map.Entry<Integer, State> o2) {
                            return o1.getKey().compareTo(o2.getKey());
                        }
                    });

            /* Print ordered state list */
            for (int i = 0; i < stateArrList.size(); i++) {
                System.out.println("State:  "
                        + stateArrList.get(i).getValue().getStateName());
                Iterator<Map.Entry<String, Set<StateAndPermission>>> it = stateArrList
                        .get(i).getValue().getTransitionRelation().entrySet()
                        .iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Set<StateAndPermission>> entry = it
                            .next();
                    for (StateAndPermission stateAndPerm : entry.getValue()) {
                        System.out.println("    " + entry.getKey() + " / "
                                + stateAndPerm.getPermission() + "  ==> "
                                + stateAndPerm.getState().getStateName());
                    }
                }
                System.out.println();
            }
            System.out
                    .println("=============================================================================================================");
            System.out
                    .println("=============================================================================================================");
        }
    }

}
