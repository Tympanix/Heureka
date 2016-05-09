package dtu.kursus02180.heureka.RouteFinding;

import java.util.Comparator;

public class HeuristicAStar implements Comparator<Crossing> {

    private Crossing goal;

    public HeuristicAStar(Crossing goal){
        this.goal = goal;
    }

    @Override
    public int compare(Crossing o1, Crossing o2) {
        return Float.compare(o1.getHeuristicDistance(this.goal), o2.getHeuristicDistance(this.goal));
    }
}
