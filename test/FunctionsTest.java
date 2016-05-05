import dtu.kursus02180.heureka.InferenceEngine.Clause;
import dtu.kursus02180.heureka.InferenceEngine.Literal;

import java.util.function.Function;

public class FunctionsTest {

    public static void main(String[] args) {


        Function<Clause, Float> heuristics = clause -> clause.getDistance() + clause.getClauseLength();

        Clause c1 = new Clause(heuristics);
        c1.setDistance(2);
        c1.addLiteral(new Literal("a", true));

        Float hej = heuristics.apply(c1);
        System.out.println(hej);
    }
}
