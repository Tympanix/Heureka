package dtu.kursus02180.heureka.Graph;


import java.util.PriorityQueue;

public abstract class Node implements Comparable<Node> {

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

    public abstract float getHeuristicDistance();

    @Override
    public int compareTo(Node o) {
        return Float.compare(this.getHeuristicDistance(), o.getHeuristicDistance());
    }

    public void relax(Node parent, Edge edge, PriorityQueue<Node> priorityQueue) {
        if (this.distance > parent.distance + edge.weight){
            this.distance = parent.distance + edge.weight;
            this.parent = parent;
            priorityQueue.remove(this);
            priorityQueue.add(this);
        }
    }
}
