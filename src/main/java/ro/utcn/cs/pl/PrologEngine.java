package ro.utcn.cs.pl;

import jpl.Query;
import jpl.Term;
import jpl.Util;

import java.util.*;
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

    public List<Map<String, int[]>> runQuery(String queryString) {
        final Query query = new Query(queryString);
        evaluate(query, queryString);
        final List<Map<String, int[]>> solutions = new ArrayList<>();
        Arrays.stream(query.allSolutions()).forEach(x -> solutions.add(convertSolution(x)));
        return solutions;
    }

    private void evaluate(Query query, String asText) {
        System.out.println(format("%s %s", asText, query.hasSolution() ? "succeeded" : "failed"));
    }

    private Map<String, int[]> convertSolution(Hashtable solution) {
        final Map<String, int[]> result = new HashMap<>();
        final Consumer<Map.Entry> addEntry = x -> result.put((String) x.getKey(), toArray((Term) x.getValue()));
        solution.entrySet().stream().forEach(addEntry);
        return result;
    }

    private int[] toArray(Term plList) {
        final Term[] terms = Util.listToTermArray(plList);
        return Arrays.stream(terms).mapToInt(Term::intValue).toArray();
    }
}