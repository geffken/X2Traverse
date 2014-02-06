package de.unifr.acp.nfa;

import static de.unifr.acp.fst.MetaCharacters.QUESTION_MARK;

import java.io.IOException;
import java.io.StringReader;
import java.util.ListIterator;

import de.unifr.acp.contract.Concat;
import de.unifr.acp.contract.Identifier;
import de.unifr.acp.contract.Or;
import de.unifr.acp.contract.Path;
import de.unifr.acp.contract.Plus;
import de.unifr.acp.contract.QMark;
import de.unifr.acp.contract.QMarkLit;
import de.unifr.acp.contract.Star;
import de.unifr.acp.contract.SuffixOp;
import de.unifr.acp.fst.MetaCharacters;
import de.unifr.acp.parser.MyParser;
import de.unifr.acp.parser.MyParser.yyException;
import de.unifr.acp.parser.MyScanner;

/**
 * An NFA with the input alphabet being the set of all strings.
 * 
 * @author geffken
 * 
 */
public class NFA {

    /**
     * Indicates the start state of the {@link NFA}.<br>
     * In order to run the NFA correctly, the start state has to be
     * given.
     */
    private final NFAState startState;

    /**
     * Indicates the the next fresh state number.
     */
    private static int freshStateNum = 0;

    /**
     * Constructs an NFA with a start state.
     * 
     */
    public NFA() {
        super();
        this.startState = genFreshState();
    }
    
    /**
     * Constructs a new APC automaton representation for a path expression.
     * 
     * @param ctrct
     *            the path expression to generate an automaton for. Paths
     *            matching the path expression are considered {R,W}, prefixes of
     *            these {R}.
     * 
     */
    public NFA(String ctrct) {
        this();
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
            NFAState end = genFreshState();

            // recursively generate this FST (interpret contract as
            // read-prefix-closed r/w contract)
            recursivePut(path, true, this.getStartState(),
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
     * specified path expression to be recognized by this machine.
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
            start.addTransition(QUESTION_MARK, end);
        } else if (path instanceof Identifier) {
            end.setFinal(isFinal);
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
                    recursivePut(element, currentIsFinal, previousState, nextState);
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

    protected NFAState genFreshState() {
        NFAState state = new NFAState(freshStateNum++);
        return state;
    }

    public NFAState getStartState() {
        return startState;
    }

}
