package de.unifr.acp.runtime.nfa;

import static de.unifr.acp.runtime.fst.MetaCharacters.QUESTION_MARK;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;
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
import de.unifr.acp.runtime.fst.MetaCharacters;
import de.unifr.acp.runtime.parser.MyParser;
import de.unifr.acp.runtime.parser.MyParser.yyException;
import de.unifr.acp.runtime.parser.MyScanner;
import de.unifr.acp.runtime.util.HashMap;

/**
 * An NFA with the input alphabet being the set of all strings.
 * 
 * @author geffken
 * 
 */
public class NFA {

    /**
     * Indicates the start state of the {@link NFA}.<br>
     * In order to run the NFA correctly, the start state has to be given.
     */
    protected final NFAState startState;

    /**
     * Indicates the the next fresh state number.
     */
    private static int freshStateNum = 0;

    private String contract = null;

    /**
     * Constructs an NFA with a start state.
     * 
     */
    public NFA() {
        super();
        this.startState = genFreshState();
    }

    /**
     * Constructs a new NFA representation for a path expression.
     * 
     * @param ctrct
     *            the path expression to generate an automaton for.
     * 
     */
    public NFA(String ctrct) {
        this();
        this.contract = ctrct;
        generate(ctrct);
    }

    /**
     * Converst this automaton to a DFA.
     * 
     * @return the corresponding DFA
     */
    public DFA toDFA() {
        DFA dfa = new DFA();
        NFAState currentDFAState = dfa.getStartState();
        Set<NFAState> currentNFAstates = Collections.singleton(this.startState);
        Map<Set<NFAState>, NFAState> stateMap = new HashMap<>();
        stateMap.put(currentNFAstates, currentDFAState);

        toDFA(currentNFAstates, stateMap, dfa);

        return dfa;
    }

    /**
     * Recursively builds all reachable (from the specified NFA states) DFA
     * states (corresponding to states in the NFA states power set).
     * 
     * @param currentNFAstates
     *            the current set of NFA states to proceed from
     * @param stateMap
     *            a map from all known NFA state sets to the correspondings DFA
     *            states
     * @param dfa the DFA (needed to generate nice state names only)
     */
    private void toDFA(Set<NFAState> currentNFAstates,
            Map<Set<NFAState>, NFAState> stateMap, DFA dfa) {

        Set<String> characters = new HashSet<>();
        for (NFAState state : currentNFAstates) {
            characters.addAll(state.getTransitionRelation().keySet());
        }
        characters.remove(MetaCharacters.EPSILON);

        for (String inputChar : characters) {
            Set<NFAState> targetNFAStates = step(currentNFAstates, inputChar);
            NFAState targetDFAState = dfa.genFreshState();
            stateMap.get(currentNFAstates).addTransition(inputChar,
                    targetDFAState);

            assert (stateMap.get(targetNFAStates) != null || !stateMap
                    .containsKey(targetNFAStates));
            if (stateMap.put(targetNFAStates, targetDFAState) == null) {
                toDFA(targetNFAStates, stateMap, dfa);
            }
        }

    }

    /**
     * Generates one weighted automaton for all path expressions in the
     * specified composite contract. Caches coaccessibility during generation.
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
            NFAState end = genFreshState();

            // recursively generate this FST (interpret contract as
            // read-prefix-closed r/w contract)
            recursivePut(path, true, this.getStartState(), end);

            // this.debugPrint();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (yyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursively generates transitions and states as required for the
     * specified path expression to be recognized by this machine. Caches
     * coaccessibility during generation.
     * 
     * @param path
     *            the abstract {@link Path} to generate machine states for.
     * @param isFinal
     *            whether the end state needs to be final
     * @param start
     *            the start state of the path to generate
     * @param the
     *            final state of the path to generate
     */
    private void recursivePut(final Path path, final boolean isFinal,
            NFAState start, NFAState end) {
        /*
         * Pattern matching on path object.
         */

        // base cases
        if (path instanceof QMarkLit) {
            end.setFinal(isFinal);
            end.setCoaccessible(true); // cache coaccessibility in construction
            start.addTransition(QUESTION_MARK, end);
        } else if (path instanceof Identifier) {
            end.setFinal(isFinal);
            end.setCoaccessible(true); // cache coaccessibility in construction
            start.addTransition(((Identifier) path).getName(), end);

            // recursive cases
        } else if (path instanceof Concat) {
            Concat concat = (Concat) path;

            ListIterator<Path> it = concat.listIterator(concat.size());
            boolean currentIsFinal = isFinal;
            NFAState nextState = end;
            NFAState previousState;
            while (it.hasPrevious()) {
                Path element = it.previous();
                if (it.hasPrevious()) {
                    previousState = genFreshState();
                    recursivePut(element, currentIsFinal, previousState,
                            nextState);
                    nextState = previousState;
                } else {
                    previousState = start;
                    recursivePut(element, currentIsFinal, start, nextState);
                }

                if (!element.isNullable()) {
                    currentIsFinal = false;
                }
            }
        } else if (path instanceof Or) {
            for (Path element : ((Or) path)) {
                NFAState startOfSingle = genFreshState();

                start.addTransition(MetaCharacters.EPSILON, startOfSingle);
                recursivePut(element, isFinal, startOfSingle, end);
            }
        } else if (path instanceof SuffixOp) {
            SuffixOp operand = (SuffixOp) path;
            // this code avoids epsilon (input) cycles altogether
            if (path instanceof Star) {
                // self loop (repetitive operator)
                // invariant: only ever loop on start states
                recursivePut(operand.getPath(), isFinal, start, start);

                // skip edge
                start.addTransition(MetaCharacters.EPSILON, end);
            } else if (path instanceof QMark) {
                recursivePut(operand.getPath(), isFinal, start, end);

                // skip edge
                start.addTransition(MetaCharacters.EPSILON, end);
            } else if (path instanceof Plus) {
                Path inner = operand.getPath();
                // on the fly AST transformation from path+ to path.path*
                Concat concat = new Concat(inner, new Star(inner));
                recursivePut(concat, isFinal, start, end);
            }

        } else {
            throw new IllegalArgumentException("Unknown path type");
        }
    }

    /**
     * Reachability-closes the specified (mutable) set of states.
     * 
     * @param states
     *            the set of states
     */
    public static void transitiveReachabilityClosure(Collection<NFAState> states) {
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

    /**
     * Caches coaccessibility is (reachable) states.
     */
    public void cacheCoaccessibility() {
        isAndSetCoaccessibility(this.startState);
    }

    private static boolean isAndSetCoaccessibility(NFAState state) {

        if (state.isFinal()) {
            state.setCoaccessible(true);
            return true;
        }

        boolean isCoaccessible = false;
        for (Set<NFAState> reachSet : state.getTransitionRelation().values()) {
            for (NFAState reachState : reachSet) {
                isCoaccessible |= isAndSetCoaccessibility(reachState);
            }
        }
        state.setCoaccessible(isCoaccessible);
        return isCoaccessible;
    }

    public static Set<NFAState> step(final Set<NFAState> inputStates,
            final String inputChar) {

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

        NFA.transitiveEpsilonClosure(result);
        return result;
    }

    public static Set<NFAState> transitiveEpsilonClosure(NFAState state) {
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
    public static void transitiveEpsilonClosure(Set<NFAState> states) {
        Set<NFAState> newStates = states;

        while (true) {
            Set<NFAState> brandNewStates = new HashSet<>();
            for (NFAState state : newStates) {
                brandNewStates.addAll(state
                        .applyTransitionRelation(MetaCharacters.EPSILON));
            }

            if (!states.addAll(brandNewStates)) {
                break;
            }
            newStates = brandNewStates;
        }
    }

    public NFAState genFreshState() {
        NFAState state = new NFAState(freshStateNum++);
        return state;
    }

    public NFAState getStartState() {
        return startState;
    }

}
