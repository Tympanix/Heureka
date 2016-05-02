import dtu.kursus02180.heureka.InferenceEngine.Clause;
import dtu.kursus02180.heureka.InferenceEngine.Literal;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;


public class HashCodeTest {

    @Test
    public void hashCodeTest(){

        HashSet<String> h = new HashSet<String>();

        h.add("bc");
        h.add("ad");

        for (Object aH : h) {
            System.out.println(aH);
        }
    }
}
