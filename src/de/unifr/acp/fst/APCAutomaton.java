package de.unifr.acp.fst;

import static de.unifr.acp.fst.MetaCharacters.QUESTION_MARK;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import de.unifr.acp.contract.Concat;
import de.unifr.acp.contract.Identifier;
import de.unifr.acp.contract.Or;
import de.unifr.acp.contract.Path;
import de.unifr.acp.contract.Plus;
import de.unifr.acp.contract.QMark;
import de.unifr.acp.contract.QMarkLit;
import de.unifr.acp.contract.Star;
import de.unifr.acp.contract.SuffixOp;
import de.unifr.acp.parser.MyParser;
import de.unifr.acp.parser.MyScanner;
import de.unifr.acp.parser.MyParser.yyException;

import static de.unifr.acp.fst.ExtPermission.valueOf;

/**
 * A weighted automaton over the {@link ExtPermission} semiring. Implicitly all
 * states are final.
 * 
 * @author geffken
 * 
 */
public final class APCAutomaton extends WeightedAutomaton<ExtPermission> {

    /**
     * The contract that this automaton was generated from.
     */
    private final String contract;

    /**
     * Constructs a new APC automaton representation for a path expression.
     * 
     * @param ctrct
     *            the path expression to generate an automaton for. Paths
     *            matching the path expression are considered {R,W}, prefixes of
     *            these {R}.
     * 
     */
    public APCAutomaton(String ctrct) {
        super();
        this.contract = ctrct;
        generate(ctrct);
    }

    /**
     * Generates one weighted automaton for all path expressions in the
     * specified composite contract.
     * 
     * @param contract
     *            the composite contract
     */
    private void generate(final String contract) {
        try {
            // parse contract
            Path path = (Path) new MyParser().yyparse(new MyScanner(
                    new StringReader(contract)));

            // generate a final state first
            WAState<ExtPermission> end = genFreshState();

            // recursively generate this FST (interpret contract as
            // read-prefix-closed r/w contract)
            recursivePut(path, valueOf(Permission.READ_WRITE), this.startState,
                    end);

            // this.debugPrint();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (yyException e) {
            e.printStackTrace();
        }
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
    private void recursivePut(final Path path, final ExtPermission permission,
            WAState<ExtPermission> start, WAState<ExtPermission> end) {
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
            ExtPermission currentPerm = permission;
            WAState<ExtPermission> nextState = end;
            WAState<ExtPermission> previousState;
            while (it.hasPrevious()) {
                Path element = it.previous();
                if (it.hasPrevious()) {
                    previousState = genFreshState();
                    recursivePut(element, currentPerm, previousState, nextState);
                    nextState = previousState;
                } else {
                    previousState = start;
                    recursivePut(element, currentPerm, start, nextState);
                }

                if (!element.isNullable()) {
                    currentPerm = ExtPermission.valueOf(Permission.READ_ONLY);
                }
            }
        } else if (path instanceof Or) {
            for (Path element : ((Or) path)) {
                WAState<ExtPermission> startOfSingle = genFreshState();

                start.addTransition(MetaCharacters.EPSILON,
                        ExtPermission.EPSILON, startOfSingle);
                recursivePut(element, permission, startOfSingle, end);
            }
        } else if (path instanceof SuffixOp) {
            SuffixOp operand = (SuffixOp) path;
            // this code avoids epsilon (input) cycles altogether
            if (path instanceof Star) {
                // self loop (repetitive operator)
                // invariant: only ever loop on start states
                recursivePut(operand.getPath(), permission, start, start);

                // skip edge
                start.addTransition(MetaCharacters.EPSILON,
                        ExtPermission.EPSILON, end);
            } else if (path instanceof QMark) {
                recursivePut(operand.getPath(), permission, start, end);

                // skip edge
                start.addTransition(MetaCharacters.EPSILON,
                        ExtPermission.EPSILON, end);
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
     * Returns the contract this automaton was generated from.
     * 
     * @return the contract
     */
    public String getContract() {
        return contract;
    }

    /**
     * Prints this weighted automaton if {@link WeightedAutomaton#debug} is
     * <code>true</code>.
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
            ArrayList<Map.Entry<Integer, WAState<ExtPermission>>> stateArrList = new ArrayList<>(
                    getStatesByName().entrySet());
            Collections
                    .sort(stateArrList,
                            new Comparator<Map.Entry<Integer, WAState<ExtPermission>>>() {
                                public final int compare(
                                        final Map.Entry<Integer, WAState<ExtPermission>> o1,
                                        final Map.Entry<Integer, WAState<ExtPermission>> o2) {
                                    return o1.getKey().compareTo(o2.getKey());
                                }
                            });

            /* Print ordered state list */
            for (int i = 0; i < stateArrList.size(); i++) {
                System.out.println("State:  "
                        + stateArrList.get(i).getValue().getStateName());
                Iterator<Map.Entry<String, Set<StateAndWeight<ExtPermission>>>> it = stateArrList
                        .get(i).getValue().getTransitionRelation().entrySet()
                        .iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Set<StateAndWeight<ExtPermission>>> entry = it
                            .next();
                    for (StateAndWeight<ExtPermission> stateAndPerm : entry
                            .getValue()) {
                        System.out.println("    " + entry.getKey() + " / "
                                + stateAndPerm.getWeight() + "  ==> "
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
