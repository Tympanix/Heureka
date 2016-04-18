import dtu.kursus02180.heureka.InferenceEngine.Clause;
import dtu.kursus02180.heureka.InferenceEngine.Literal;
import org.junit.Test;


public class HashCodeTest {

    @Test
    public void hashCodeTest(){

        Clause clause = new Clause();
        clause.addLiteral(new Literal("a", true));
        clause.addLiteral(new Literal("b", true));
        clause.addLiteral(new Literal("c", true));

        clause.addLiteral(new Literal("d", false));
        clause.addLiteral(new Literal("e", false));
        clause.addLiteral(new Literal("f", false));


        Clause clause2 = new Clause();
        clause2.addLiteral(new Literal("b", true));
        clause2.addLiteral(new Literal("c", true));

        Clause clause3 = new Clause();
        clause3.addLiteral(new Literal("a", true));
        clause3.addLiteral(new Literal("d", true));

        System.out.println(clause.getUniqueIdentifier());
        System.out.println(clause2.getUniqueIdentifier());
        System.out.println(clause3.getUniqueIdentifier());

        System.out.println(clause.hashCode());
        System.out.println(clause2.hashCode());
        System.out.println(clause3.hashCode());
    }
}
