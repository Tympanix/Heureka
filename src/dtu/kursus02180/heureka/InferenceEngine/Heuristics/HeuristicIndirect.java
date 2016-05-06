package dtu.kursus02180.heureka.InferenceEngine.Heuristics;

import dtu.kursus02180.heureka.InferenceEngine.Clause;

import java.util.Comparator;

public class HeuristicIndirect implements Comparator<Clause> {

    @Override
    public int compare(Clause c1, Clause c2) {
        return Float.compare(heuristicsFunction(c1), heuristicsFunction(c2));
    }

    public float heuristicsFunction(Clause clause){
        return clause.getDistance() + 2 * clause.getClauseLength();
    }
}
