package de.unifr.acp.runtime.fst;

import static org.junit.Assert.assertEquals;
import static de.unifr.acp.runtime.fst.Permission.NONE;
import static de.unifr.acp.runtime.fst.Permission.READ_ONLY;
import static de.unifr.acp.runtime.fst.Permission.READ_WRITE;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * @author Mohammad Shahabi
 * @author geffken
 *
 */
public class FSTTest {

    /**
     * Alternatives.
     */
    @Test
    public final void testRunAlternatives() {
        FST machine = new FST("(x|y).(a|b).(c|d|e) , x.b.(f|g|h)");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(READ_ONLY+","+READ_ONLY+","+READ_WRITE, runner.runFromScratch("x.a.c"));
        assertEquals(READ_ONLY+","+READ_ONLY+","+READ_WRITE+","+NONE, runner.runFromScratch("x.a.c.f"));
        assertEquals(READ_ONLY+","+READ_ONLY, runner.runFromScratch("x.b"));
        assertEquals(READ_ONLY+","+READ_ONLY+","+READ_WRITE, runner.runFromScratch("x.b.f"));
        machine = new FST("(x|y).(a|b).(c|d|e)");
        runner = new FSTRunner(machine);
        assertEquals(READ_ONLY+","+READ_ONLY+","+NONE, runner.runFromScratch("x.b.f"));
        
        FST machine2 = new FST("x.a.(a)*.a");
        FSTRunner runner2 = new FSTRunner(machine2);
        assertEquals(READ_ONLY+","+READ_ONLY+","+READ_WRITE+","+READ_WRITE, runner2.runFromScratch("x.a.a.a."));
    }
    
    /**
     * Possible collisions with meta-symbols.
     */
    @Test
    public final void testRunCollisions() {
        FST machine = new FST("x.EPSI");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(READ_ONLY+","+READ_WRITE, runner.runFromScratch("x.EPSI"));
    }

    /**
     * Expressions containing Questionmark.
     */
    @Test
    public final void testRunQuestion() {
        FST machine = new FST("x.?");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(READ_ONLY.toString(), runner.runFromScratch("x"));
        assertEquals(READ_ONLY+","+READ_WRITE, runner.runFromScratch("x.a"));
        assertEquals(READ_ONLY+","+READ_WRITE+","+NONE, runner.runFromScratch("x.a.b"));
        machine = new FST("x.?.a");
        runner = new FSTRunner(machine);
        assertEquals(READ_ONLY+","+READ_ONLY+","+READ_WRITE, runner.runFromScratch("x.g.a"));
        assertEquals(READ_ONLY+","+READ_ONLY+","+NONE, runner.runFromScratch("x.g.b"));
    }

    /**
     * Expressions containing Star.
     */
    @Test
    public final void testRunStar() {
        FST machine = new FST("x.*");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(READ_WRITE.toString(), runner.runFromScratch("x"));
        assertEquals(READ_WRITE+","+READ_WRITE, runner.runFromScratch("x.a"));
        assertEquals(READ_WRITE+","+READ_WRITE+","+READ_WRITE, runner.runFromScratch("x.a.b"));
        machine = new FST("x.c.*");
        runner = new FSTRunner(machine);
        assertEquals(READ_ONLY.toString(), runner.runFromScratch("x"));
        assertEquals(READ_ONLY+","+READ_WRITE, runner.runFromScratch("x.c"));
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
    
    public static String toPermissionsString(Permission... permisssions) {
        return toSeparatedString(",", permisssions);
    }
    
    /**
     * Expressions containing + modifier.
     */
    @Test
    public final void testRunPlus() {
        FST machine = new FST("x.a+");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(toPermissionsString(READ_ONLY), runner.runFromScratch("x"));
        assertEquals(toPermissionsString(READ_ONLY,READ_WRITE), runner.runFromScratch("x.a"));
        assertEquals(toPermissionsString(READ_ONLY,READ_WRITE,READ_WRITE), runner.runFromScratch("x.a.a"));
        assertEquals(toPermissionsString(READ_ONLY,READ_WRITE,NONE), runner.runFromScratch("x.a.b"));
        machine = new FST("x.a+.b+");
        runner = new FSTRunner(machine);
        assertEquals(toPermissionsString(READ_ONLY), runner.runFromScratch("x"));
        assertEquals(toPermissionsString(READ_ONLY,READ_ONLY), runner.runFromScratch("x.a"));
        assertEquals(toPermissionsString(READ_ONLY,READ_ONLY,READ_ONLY), runner.runFromScratch("x.a.a"));
        assertEquals(toPermissionsString(READ_ONLY,READ_ONLY,READ_WRITE), runner.runFromScratch("x.a.b"));
        machine = new FST("x.a+.c*");
        runner = new FSTRunner(machine);
        assertEquals(toPermissionsString(READ_ONLY), runner.runFromScratch("x"));
        assertEquals(toPermissionsString(READ_ONLY,READ_WRITE), runner.runFromScratch("x.a"));
        assertEquals(toPermissionsString(READ_ONLY,READ_WRITE,READ_WRITE), runner.runFromScratch("x.a.c"));
        assertEquals(toPermissionsString(READ_ONLY,READ_WRITE,READ_WRITE, NONE), runner.runFromScratch("x.a.c.a"));
    }

    /**
     * Expressions containing PropertyStar.
     */
    @Test
    public final void testRunPropStar() {
        FST machine = new FST("x.(a|b)*");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(READ_WRITE.toString(), runner.runFromScratch("x"));
        assertEquals(READ_WRITE+","+READ_WRITE+","+READ_WRITE, runner.runFromScratch("x.a.b"));
        assertEquals(READ_WRITE+","+READ_WRITE+","+NONE, runner.runFromScratch("x.a.e"));
        assertEquals(READ_WRITE+","+NONE, runner.runFromScratch("x.c"));
        assertEquals(READ_WRITE+","+NONE+","+NONE, runner.runFromScratch("x.c.a"));
        List<Permission> perms = Arrays.asList(new Permission[]{READ_ONLY, READ_WRITE});
        
        
        machine = new FST("x.c*.(a|b)*");
        runner = new FSTRunner(machine);
        assertEquals(READ_WRITE.toString(), runner.runFromScratch("x"));
        
        machine = new FST("x.c.(a|b)*");
        runner = new FSTRunner(machine);
        assertEquals(READ_ONLY+","+READ_WRITE, runner.runFromScratch("x.c"));
        assertEquals(NONE.toString(), runner.runFromScratch("y"));
        assertEquals(READ_ONLY.toString(), runner.runFromScratch("x"));
    }
    
    /**
     * Read-only expressions.
     */
    @Test
    public final void testReadOnly() {
        FST machine = new FST("x.a.b.c.@");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(READ_ONLY.toString(), runner.runFromScratch("x"));
        assertEquals(READ_ONLY + "," + READ_ONLY + "," + READ_ONLY + ","
                + READ_ONLY, runner.runFromScratch("x.a.b.c"));
        assertEquals(NONE + "," + NONE + "," + NONE + "," + NONE,
                runner.runFromScratch("y.a.b.c"));
    }

    /**
     * Combination of all expressions.
     */
    @Test
    public final void testRunCombined() {
        FST machine = new FST("x.(a|b).?.c.*.(d|e|f).(g|h)*.i");
        FSTRunner runner = new FSTRunner(machine);
        assertEquals(READ_ONLY.toString(), runner.runFromScratch("x"));
        assertEquals(READ_ONLY + "," + READ_ONLY + "," + READ_ONLY + ","
                + READ_ONLY, runner.runFromScratch("x.a.b.c"));
        assertEquals(NONE + "," + NONE + "," + NONE + "," + NONE,
                runner.runFromScratch("y.a.b.c"));
    }

    /**
     * For testing the {@link FSTRunner#resetAndStep(String)}.
     */
    @Test
    public final void testStep() {
        FST fst = new FST("(x|y).(a|b).(c|d|e).(i|j|k)");
        FSTRunner runner = new FSTRunner(fst);
        fst.debugPrint();
        assertEquals(READ_ONLY, runner.step("x"));
        assertEquals(READ_ONLY, runner.step("b"));
        assertEquals(READ_ONLY, runner.step("c"));
        FST machine2 = new FST("x.b.(f|g|h)");
        runner = new FSTRunner(machine2);
        assertEquals(NONE, runner.step("j"));
        assertEquals(NONE, runner.step("k"));
        runner.reset();
        assertEquals(READ_ONLY, runner.step("x"));
        assertEquals(READ_ONLY, runner.step("b"));
        assertEquals(READ_WRITE, runner.step("f"));
    }

    /**
     * Just testing the run.
     */
    @Test
    public final void justRun() {
        FST machine = new FST("x.(a|b).?.*.(g|h)*.i");
        FSTRunner runner = new FSTRunner(machine);
        runner.runFromScratch("x.b.c.f.i");
    }
}
