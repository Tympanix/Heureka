package dtu.kursus02180.heureka.RouteFinding;

import dtu.kursus02180.heureka.Graph.Edge;

public class Street extends Edge {

    String name;

    public Street(String name, Crossing from, Crossing to) {
        super(from, to, from.getDistanceTo(to));
        this.name = name;
    }

}
