package ro.utcn.cs.pl;

public class Test {
    public static void main(String[] argv) {
        final PrologEngine engine = PrologEngine.init("pl/logic.pl");
        engine.runQuery("middle([1, 2, 3, 4, 5, 6, 7, 8], M)");
    }
}
