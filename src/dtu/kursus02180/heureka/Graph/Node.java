package dtu.kursus02180.heureka.Graph;


import java.util.Queue;

public abstract class Node {

    float distance;
    Node parent;

    public float getDistance() {
        return distance;
    }

    public Node getParent() {
        return parent;
    }

    public Node(){
        this.parent = null;
        this.distance = Float.MAX_VALUE;
    }

    public void setDistance(float distance){
        this.distance = distance;
    }

    @SuppressWarnings("unchecked")
    public <T extends Node> void relax(T parent, Edge edge, Queue<T> queue) {
        if (this.distance > parent.distance + edge.weight){
            this.distance = parent.distance + edge.weight;
            this.parent = parent;
            queue.remove(this);
            queue.add((T) this);
        }
    }
}
