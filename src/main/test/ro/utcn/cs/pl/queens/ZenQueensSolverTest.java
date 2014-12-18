package ro.utcn.cs.pl.queens;

import org.junit.Test;
import ro.utcn.cs.pl.queens.ZenQueensSolver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ZenQueensSolverTest {

    @Test
    public void testSolve() throws Exception {
        final ZenQueensSolver solver = new ZenQueensSolver();
        final List<Map<String, int[]>> solutions = solver.solve();
        assertEquals(92, solutions.size());
    }
}