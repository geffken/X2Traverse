package de.unifr.acp.fst;

import static de.unifr.acp.fst.Permission.NONE;
import static de.unifr.acp.fst.Permission.READ_ONLY;
import static de.unifr.acp.fst.Permission.READ_WRITE;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class APCAutomatonTest {

    /**
     * Alternatives.
     */
    @Test
    public final void testRunAlternatives() {
        APCAutomaton machine = new APCAutomaton(
                "(x|y).(a|b).(c|d|e) , x.b.(f|g|h)");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_WRITE, runner.runFromScratch("x.a.c"));
        assertEquals(NONE, runner.runFromScratch("x.a.c.f"));
        assertEquals(READ_ONLY, runner.runFromScratch("x.b"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.b.f"));
        machine = new APCAutomaton("(x|y).(a|b).(c|d|e)");
        runner = new APCRunner(machine);
        assertEquals(NONE, runner.runFromScratch("x.b.f"));

        APCAutomaton machine2 = new APCAutomaton("x.a.(a)*.a");
        APCRunner runner2 = new APCRunner(machine2);
        assertEquals(READ_WRITE, runner2.runFromScratch("x.a.a.a."));
    }

    /**
     * Possible collisions with meta-symbols.
     */
    @Test
    public final void testRunCollisions() {
        APCAutomaton machine = new APCAutomaton("x.EPSI");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_WRITE, runner.runFromScratch("x.EPSI"));
    }

    /**
     * Expressions containing Questionmark.
     */
    @Test
    public final void testRunQuestion() {
        APCAutomaton machine = new APCAutomaton("x.?");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a"));
        assertEquals(NONE, runner.runFromScratch("x.a.b"));
        machine = new APCAutomaton("x.?.a");
        runner = new APCRunner(machine);
        assertEquals(READ_WRITE, runner.runFromScratch("x.g.a"));
        assertEquals(NONE, runner.runFromScratch("x.g.b"));
    }

    /**
     * Expressions containing Star.
     */
    @Test
    public final void testRunStar() {
        APCAutomaton machine = new APCAutomaton("x.*");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_WRITE, runner.runFromScratch("x"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a.b"));
        machine = new APCAutomaton("x.c.*");
        runner = new APCRunner(machine);
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.c"));
    }

    public static <T> String toSeparatedString(String separator, T... elements) {
        List<T> list = Arrays.asList(elements);
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = list.iterator();
        if (it.hasNext()) {
            sb.append(it.next());
        }
        while (it.hasNext()) {
            sb.append(separator);
            sb.append(it.next());
        }
        return sb.toString();
    }

    @Deprecated
    public static String toPermissionsString(Permission... permisssions) {
        return toSeparatedString(",", permisssions);
    }

    /**
     * Expressions containing + modifier.
     */
    @Test
    public final void testRunPlus() {
        APCAutomaton machine = new APCAutomaton("x.a+");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a.a"));
        assertEquals(NONE, runner.runFromScratch("x.a.b"));
        machine = new APCAutomaton("x.a+.b+");
        runner = new APCRunner(machine);
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
        assertEquals(READ_ONLY, runner.runFromScratch("x.a"));
        assertEquals(READ_ONLY, runner.runFromScratch("x.a.a"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a.b"));
        machine = new APCAutomaton("x.a+.c*");
        runner = new APCRunner(machine);
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a.c"));
        assertEquals(NONE, runner.runFromScratch("x.a.c.a"));
    }

    /**
     * Expressions containing PropertyStar.
     */
    @Test
    public final void testRunPropStar() {
        APCAutomaton machine = new APCAutomaton("x.(a|b)*");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_WRITE, runner.runFromScratch("x"));
        assertEquals(READ_WRITE, runner.runFromScratch("x.a.b"));
        assertEquals(NONE, runner.runFromScratch("x.a.e"));
        assertEquals(NONE, runner.runFromScratch("x.c"));
        assertEquals(NONE, runner.runFromScratch("x.c.a"));

        machine = new APCAutomaton("x.c*.(a|b)*");
        runner = new APCRunner(machine);
        assertEquals(READ_WRITE, runner.runFromScratch("x"));

        machine = new APCAutomaton("x.c.(a|b)*");
        runner = new APCRunner(machine);
        assertEquals(READ_WRITE, runner.runFromScratch("x.c"));
        assertEquals(NONE, runner.runFromScratch("y"));
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
    }

    /**
     * Read-only expressions.
     */
    @Test
    public final void testReadOnly() {
        APCAutomaton machine = new APCAutomaton("x.a.b.c.@");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
        assertEquals(READ_ONLY, runner.runFromScratch("x.a.b.c"));
        assertEquals(NONE, runner.runFromScratch("y.a.b.c"));
    }

    /**
     * Combination of all expressions.
     */
    @Test
    public final void testRunCombined() {
        APCAutomaton machine = new APCAutomaton(
                "x.(a|b).?.c.*.(d|e|f).(g|h)*.i");
        APCRunner runner = new APCRunner(machine);
        assertEquals(READ_ONLY, runner.runFromScratch("x"));
        assertEquals(READ_ONLY, runner.runFromScratch("x.a.b.c"));
        assertEquals(NONE, runner.runFromScratch("y.a.b.c"));
    }

    /**
     * For testing the {@link APCRunner#resetAndStep(String)}.
     */
    @Test
    public final void testStep() {
        APCAutomaton PermissionAutomaton = new APCAutomaton(
                "(x|y).(a|b).(c|d|e).(i|j|k)");
        APCRunner runner = new APCRunner(PermissionAutomaton);
        PermissionAutomaton.debugPrint();
        assertEquals(READ_ONLY, runner.step("x").toPermission());
        assertEquals(READ_ONLY, runner.step("b").toPermission());
        assertEquals(READ_ONLY, runner.step("c").toPermission());
        APCAutomaton machine2 = new APCAutomaton("x.b.(f|g|h)");
        runner = new APCRunner(machine2);
        assertEquals(NONE, runner.step("j").toPermission());
        assertEquals(NONE, runner.step("k").toPermission());
        runner.reset();
        assertEquals(READ_ONLY, runner.step("x").toPermission());
        assertEquals(READ_ONLY, runner.step("b").toPermission());
        assertEquals(READ_WRITE, runner.step("f").toPermission());
    }

    /**
     * Just testing a run does not throw any exception.
     */
    @Test
    public final void runDoesNotThrow() {
        APCAutomaton machine = new APCAutomaton(
                "x.(a|b).?.*.(g|h)*.i");
        APCRunner runner = new APCRunner(machine);
        runner.runFromScratch("x.b.c.f.i");
    }
}
