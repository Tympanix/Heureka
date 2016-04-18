package dtu.kursus02180.heureka.Graph;


import java.util.PriorityQueue;

public abstract class Node implements Comparable<Node> {

    public float getDistance() {
        return distance;
    }

    float distance;
    float distanceToGoal;
    Node parent;

    public Node(){
        this.parent = null;
        this.distance = Float.MAX_VALUE;
        this.distanceToGoal = 0;
    }

    public abstract float getDistanceTo(Node goal);

    public boolean isSeen(){
        return distance != Float.MAX_VALUE;
    }

    public void setDistance(float distance){
        this.distance = distance;
    }

    public float getHeuristicDistance(){
        return this.distance + this.distanceToGoal;
    }

    @Override
    public int compareTo(Node o) {
        return Float.compare(getHeuristicDistance(), o.getHeuristicDistance());
    }

    public void relax(Node parent, Node goal, Edge edge, PriorityQueue<Node> priorityQueue) {
        if (this.distance > parent.distance + edge.weight){
            this.distance = parent.distance + edge.weight;
            this.parent = parent;
            this.distanceToGoal = this.getDistanceTo(goal);
            priorityQueue.remove(this);
            priorityQueue.add(this);
        }
    }
}
