package de.unifr.acp.runtime.nfa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NFATest {
    /**
     * Alternatives.
     */
    @Test
    public final void testRunAlternatives() {
        NFA machine = new NFA("(x|y).(a|b).(c|d|e) , x.b.(f|g|h)");
        NFARunner runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x.a.c").isFinal());
        assertFalse(runner.runFromScratch("x.a.c.f").isCoaccessible());
        assertTrue(runner.runFromScratch("x.b").isCoaccessible());
        assertTrue(runner.runFromScratch("x.b.f").isFinal());

        machine = new NFA("(x|y).(a|b).(c|d|e)");
        runner = new NFARunner(machine);
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("x.b.f")));

        machine = new NFA("x.a.(a)*.a");
        runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x.a.a.a.").isFinal());
    }

    private static boolean isCoaccessibleAndNotFinal(NFARunner runner) {
        return runner.isCoaccessible() && !runner.isFinal();
    }

    private static boolean isNotCoaccessibleAndNotFinal(NFARunner runner) {
        return !runner.isCoaccessible() && !runner.isFinal();
    }

    /**
     * Possible collisions with meta-symbols.
     */
    @Test
    public final void testRunCollisions() {
        // EPSI is supposed to be treated as a normal character
        NFA machine = new NFA("x.EPSI");
        NFARunner runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x.EPSI").isFinal());
    }

    /**
     * Expressions containing Questionmark.
     */
    @Test
    public final void testRunQuestion() {
        NFA machine = new NFA("x.?");
        NFARunner runner = new NFARunner(machine);
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(runner.runFromScratch("x.a").isFinal());
        assertTrue(!runner.runFromScratch("x.a.b").isFinal()
                && !runner.isCoaccessible());
        machine = new NFA("x.?.a");
        runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x.g.a").isFinal());
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("x.g.b")));
    }

    /**
     * Expressions containing Star.
     */
    @Test
    public final void testRunStar() {
        NFA machine = new NFA("x.*");
        NFARunner runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x").isFinal());
        assertTrue(runner.runFromScratch("x.a").isFinal());
        assertTrue(runner.runFromScratch("x.a.b").isFinal());
        machine = new NFA("x.c.*");
        runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x").isCoaccessible()
                && !runner.isFinal());
        assertTrue(runner.runFromScratch("x.c").isFinal());
    }

    // public static String toPermissionsString(Permission... permisssions) {
    // return toSeparatedString(",", permisssions);
    // }
    //

    /**
     * Expressions containing + modifier.
     */
    @Test
    public final void testRunPlus() {
        NFA machine = new NFA("x.a+");
        NFARunner runner = new NFARunner(machine);
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(runner.runFromScratch("x.a").isFinal());
        assertTrue(runner.runFromScratch("x.a.a").isFinal());
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("x.a.b")));
        machine = new NFA("x.a+.b+");
        runner = new NFARunner(machine);
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x.a")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x.a.a")));
        assertTrue(runner.runFromScratch("x.a.b").isFinal());
        machine = new NFA("x.a+.c*");
        runner = new NFARunner(machine);
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(runner.runFromScratch("x.a").isFinal());
        assertTrue(runner.runFromScratch("x.a.c").isFinal());
        assertTrue(isNotCoaccessibleAndNotFinal(runner
                .runFromScratch("x.a.c.a")));
    }

    /**
     * Expressions containing PropertyStar.
     */
    @Test
    public final void testRunPropStar() {
        NFA machine = new NFA("x.(a|b)*");
        NFARunner runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x").isFinal());
        assertTrue(
                runner.runFromScratch("x.a.b").isFinal());
        assertTrue(isNotCoaccessibleAndNotFinal(
                runner.runFromScratch("x.a.e")));
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("x.c")));
        assertTrue(isNotCoaccessibleAndNotFinal(
                runner.runFromScratch("x.c.a")));

        machine = new NFA("x.c*.(a|b)*");
        runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x").isFinal());

        machine = new NFA("x.c.(a|b)*");
        runner = new NFARunner(machine);
        assertTrue(runner.runFromScratch("x.c").isFinal());
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("y")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
    }

    /**
     * Read-only expressions.
     */
    @Test
    public final void testReadOnly() {
        NFA machine = new NFA("x.a.b.c.@");
        NFARunner runner = new NFARunner(machine);
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x.a.b.c")));
        assertTrue(isNotCoaccessibleAndNotFinal(runner
                .runFromScratch("y.a.b.c")));
    }

    /**
     * Combination of all expressions.
     */
    @Test
    public final void testRunCombined() {
        NFA machine = new NFA("x.(a|b).?.c.*.(d|e|f).(g|h)*.i");
        NFARunner runner = new NFARunner(machine);
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x.a.b.c")));
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("y.a.b.c")));
    }

    /**
     * For testing the {@link NFARunner#resetAndStep(String)}.
     */
    @Test
    public final void testStep() {
        NFA nfa = new NFA("(x|y).(a|b).(c|d|e).(i|j|k)");
        NFARunner runner = new NFARunner(nfa);
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x.b")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x.b.c")));
        NFA machine2 = new NFA("x.b.(f|g|h)");
        runner = new NFARunner(machine2);
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("j")));
        assertTrue(isNotCoaccessibleAndNotFinal(runner.runFromScratch("j.k")));
        runner.reset();
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x")));
        assertTrue(isCoaccessibleAndNotFinal(runner.runFromScratch("x.b")));
        assertTrue(runner.runFromScratch("x.b.f").isFinal());
    }

    /**
     * Just testing the run.
     */
    @Test
    public final void justRun() {
        NFA machine = new NFA("x.(a|b).?.*.(g|h)*.i");
        NFARunner runner = new NFARunner(machine);
        runner.runFromScratch("x.b.c.f.i");
    }
}
