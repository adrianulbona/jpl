package ro.utcn.cs.pl;

import jpl.Query;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.Consumer;

import static java.lang.String.format;

/**
 * Created by Adrian Bona.
 */
public class PrologEngine {
    private PrologEngine() {
    }

    public static PrologEngine init(String fileToLoad) {
        final PrologEngine engine = new PrologEngine();
        final String queryString = format("consult('%s')", fileToLoad);
        engine.evaluate(new Query(queryString), queryString);
        return engine;
    }

    public void runQuery(String queryString) {
        final Query query = new Query(queryString);
        evaluate(query, queryString);
        showSolutions(query);
    }

    private void evaluate(Query query, String asText) {
        System.out.println(format("%s %s", asText, query.hasSolution() ? "succeeded" : "failed"));
    }

    private void showSolutions(Query query) {
        final Consumer<Map.Entry> printEntry = x -> System.out.println(format("%s = %s", x.getKey(), x.getValue()));
        final Consumer<Hashtable> printSolution = x -> x.entrySet().stream().forEach(printEntry);
        Arrays.stream(query.allSolutions()).forEach(printSolution);
    }
}
