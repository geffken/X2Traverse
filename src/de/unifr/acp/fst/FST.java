package de.unifr.acp.fst;

import static de.unifr.acp.fst.State.EPSILON;
import static de.unifr.acp.fst.MetaCharacters.QUESTION_MARK;

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

import de.unifr.acp.annot.Grant;
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
import de.unifr.acp.parser.MyParser.yyException;
import de.unifr.acp.parser.MyScanner;


/**
 * This class represents a finite state transducer. Supports epsilon transitions
 * in the input component of the transitions relation. Currently no support for
 * epsilons in the output component of the transition relation.
 * Generation from contract avoids epsilon cycles. No support for final states.
 * 
 * @author geffken
 * @author Mohammad Shahabi
 */
public class FST {

    /**
     * To see the generated ({@link FST}).
     * @see FST#debugPrint()
     */
    public static boolean debug = true;

    /**
     * The allowed {@link Alphabet}.
     */
    private Alphabet inputAlphabet;

    /**
     * States of this state machine indexed by state name.
     * Used for debug printing this {@link FST}.
     * Redundant information if only reachable states need to be printed.
     */
    private Map<Integer, State> statesByName = new HashMap<Integer, State>();

    /**
     * During {@link FST} generation this field saves the last-generated
     * state. Instead, the recursive generator method could be extended with an
     * additional {@link State} parameter and return type {@link State}.
     */
    private State lastGenSt;

    /**
     * The Contracts that the {@link FST} should be made out of.
     */
    private String contracts;

    /**
     * Indicates the start state of the {@link FST}.<br>
     * In order to run the {@link FST} correctly, the
     * start state has to be given.
     */
    private State startState;
    
    /**
     * Indicates the the next fresh number of a State.
     */
    private int freshStateNum = 0;
    
    /**
     * @return {@link FST#inputAlphabet}
     */
    public final Alphabet getInputAlphabet() {
        return inputAlphabet;
    }

    /**
     * @param input to set the {@link FST}
     *         an inputAlphabet
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
     * @param startSt to set the {@link FST} a startState
     */
    private void setStartState(final State startSt) {
        this.startState = startSt;
    }

    /**
     * @return {@link FST#contracts}
     */
    public String getContracts() {
        return contracts;
    }

    /**
     * @param ctrts to set contracts for the {@link FST}
     */
    private void setContracts(final String ctrts) {
        this.contracts = ctrts;
    }

    /**
     * Create a new machine representation for the specified contracts.
     * 
     * @param ctrcts
     *            the contracts to generate a machine representation for
     */
    public FST(final String ctrcts) {

        // generate start state (by convention has number 0 to simplify debugging)
        State startState = addFreshState();
        setStartState(startState);

         // set last-generated state to start state to prepare FST generation
        lastGenSt = startState;
        
        // set standard ASCII-based input alphabet
        setInputAlphabet(new Alphabet());
        this.setContracts(ctrcts);
        generate(ctrcts);
    }
    
    /**
     * Generates one FST for all path expressions in the specified composite
     * contract.
     * 
     * @param contract
     *            the composite contract
     * @see FST#generateSingle(String)
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
     * Prints this {@link FST}
     * if {@link FST#debug} is <code>true</code>.
     */
    public void debugPrint() {
        if (debug) {
            System.out.println("=============================================================================================================");
            System.out.println("=============================================================================================================");

            /*
             * Orders mapping by number of state.
             */
            ArrayList<Map.Entry<Integer, State>> stateArrList = new ArrayList<Entry<Integer, State>>(statesByName.entrySet());
            Collections.sort(stateArrList, new Comparator<Map.Entry<Integer, State>>() {
                public final int compare(final Map.Entry<Integer, State> o1, final Map.Entry<Integer, State> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            
            /* Print ordered state list */
            for (int i = 0; i < stateArrList.size(); i++) {
                System.out.println("State:  " + stateArrList.get(i).getValue().getStateName());
                Iterator<Map.Entry<String, Set<StateAndPermission>>> it = stateArrList.get(i).getValue().getTransitionRelation().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Set<StateAndPermission>> entry = it.next();
                    for (StateAndPermission stateAndPerm : entry.getValue()) {
                        System.out.println("    " + entry.getKey() + " / " + stateAndPerm.getPermission() + "  ==> " + stateAndPerm.getState().getStateName());
                    }
                }
                System.out.println();
            }
            System.out.println("=============================================================================================================");
            System.out.println("=============================================================================================================");
        }
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
        recursivePut(path, permission, addFreshState());
    }

    /**
     * Recursively generates transitions and states as required for the
     * specified path in this machine. The given path is interpreted such that
     * locations represented by concrete paths matching the abstract path have
     * the specified permission and locations matching proper prefixes of
     * matching paths are readable.
     * 
     * @param path
     *            the abstract {@link Path} to generate machine states for.
     * @param permission
     *            the {@link Permission} to use for the paths matching the
     *            abstract path
     * @param nextSt
     *            the state to link the generated transitions to
     */
    private void recursivePut(final Path path, final Permission permission, State nextSt) {
        
        // remember current state
        State currentSt = lastGenSt;

        /*
         * Pattern matching on path object.
         */
        
        // base cases
        if (path instanceof QMarkLit) {
            currentSt.addTransition(QUESTION_MARK, permission, nextSt);
            lastGenSt = nextSt;
        } else if (path instanceof Identifier) {
            currentSt.addTransition(((Identifier) path).getName(),
                    permission, nextSt);
            lastGenSt = nextSt;

        // recursive cases            
        } else if (path instanceof Concat) {
            Concat concat = (Concat) path;
            
//            int lastNonNullableIndex = -1;
//            ListIterator<Path> it = concat.listIterator(concat.size());
//            assert (concat.size() >= 1);
//            while (it.hasPrevious()) {
//                final int index = it.previousIndex();
//                Path element = it.previous();
//                if (!element.isNullable()) {
//                    lastNonNullableIndex = index;
//                    break;
//                }
//            }
            Iterator<Path> it = concat.iterator();
            while (it.hasNext()) {
                //final int index = it.nextIndex();
                Path element = it.next();
                if (it.hasNext()) {
                    recursivePut(element, Permission.READ_ONLY);
                } else {
                    recursivePut(element, permission, nextSt);
                }
            }
        } else if (path instanceof Or) {
            nextSt = (nextSt == null) ? addFreshState() : nextSt;
            for (Path element : ((Or) path)) {
                State startOfSingle = addFreshState();
                currentSt.addTransition(State.EPSILON,
                        Permission.NONE, startOfSingle);
                lastGenSt = startOfSingle;
                recursivePut(element, permission, nextSt);
//                lastGenSt.addTransition(State.EPSILON,
//                        Permission.NONE, nextSt);
            }
            //lastGenSt = nextSt;
        } else if (path instanceof SuffixOp) {
            // this code avoids epsilon (input) cycles altogether
            if (path instanceof Star) {
                // self edge (repetitive operator)
                recursivePut(((Star) path).getPath(), permission, currentSt);   
                
                // skip edge (nullable: output needs to be current permission)
                currentSt.addTransition(State.EPSILON, permission, nextSt);
            } else if (path instanceof QMark) {
                recursivePut(((Star) path).getPath(), permission, nextSt);

                // skip edge (nullable: output needs to be current permission)
                currentSt.addTransition(State.EPSILON, permission, nextSt);
            } else if (path instanceof Plus) {
                recursivePut(((Star) path).getPath(), permission, nextSt);
                // back edge (repetitive operator)
                lastGenSt.addTransition(State.EPSILON, Permission.NONE, currentSt);
            }
            
            // this code can lead to epsilon (input) cycles with we want to avoid
//          recursivePut(((NewStar) path).getPath(), permission);            
//            // skip edge (output needs to be this path's permission as it is nullable)
//            if (path instanceof NewStar || path instanceof NewQMark) {
//                currentSt.addTransition(EPSILON, permission, lastGenSt);
//            }
//            
//            // back edge (repetitive operator)
//            if (path instanceof NewStar || path instanceof NewPlus) {
//                lastGenSt.addTransition(EPSILON, Permission.NONE,
//                        currentSt);
//            }
        } else {
            throw new IllegalArgumentException("Unknown path type");
        }
    }
    
}
