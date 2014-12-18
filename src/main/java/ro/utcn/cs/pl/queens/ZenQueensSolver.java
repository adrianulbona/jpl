package ro.utcn.cs.pl.queens;

import ro.utcn.cs.pl.PrologEngine;

import java.util.List;
import java.util.Map;

/**
 * @author Adrian Bona.
 */
public class ZenQueensSolver {

    private final PrologEngine engine;

    public ZenQueensSolver() {
        this.engine = PrologEngine.init("pl/queens.pl");
    }

    public List<Map<String, int[]>> solve() {
        return engine.runQuery("find_zen_queens([0,1,2,3,4,5,6,7], ZenBoard)");
    }
}
