package dtu.kursus02180.heureka.RouteFinding;


import dtu.kursus02180.heureka.Graph.Node;

public class Crossing extends Node {

    int x;
    int y;

    public Crossing(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public float getHeuristicDistance(Crossing goal){
        return this.distance + this.getDistanceTo(goal);
    }

    public int getDistanceTo(Crossing other){
        int deltaX = this.x - other.x;
        int deltaY = this.y - other.y;
        return (deltaX*deltaX + deltaY*deltaY);
    }

    @Override
    public String toString() {
        return "Node (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Crossing){
            Crossing other = (Crossing) obj;
            return (x == other.x && y == other.y);
        }

        return super.equals(obj);
    }

}
