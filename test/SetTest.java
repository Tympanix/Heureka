import dtu.kursus02180.heureka.InferenceEngine.Literal;
import org.junit.Test;

import java.util.HashSet;

public class SetTest {

    @Test
    public void setTest(){
        HashSet<Literal> set = new HashSet<Literal>();

        Literal dub = new Literal("h", true);

        set.add(new Literal("a", true));
        set.add(new Literal("b", true));
        set.add(new Literal("c", true));
        set.add(new Literal("d", true));

        // Duplicates
        set.add(new Literal("c", true));
        set.add(new Literal("c", true));
        set.add(new Literal("c", true));
        set.add(new Literal("c", true));
        set.add(new Literal("c", true));

        for (Literal l : set){
            System.out.println(l);
        }
    }

}
