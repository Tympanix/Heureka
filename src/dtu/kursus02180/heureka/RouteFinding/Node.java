package dtu.kursus02180.heureka.RouteFinding;


import java.util.PriorityQueue;

public class Node implements Comparable<Node> {

    float distance;
    float distanceToGoal;
    Node parent;
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.parent = null;
        this.distance = Float.MAX_VALUE;
        this.distanceToGoal = 0;
    }

    public boolean isSeen(){
        return distance != Float.MAX_VALUE;
    }

    public void setGoalDistance(Node goal){
        distance = getDistanceTo(goal);
    }

    public void setDistance(float distance){
        this.distance = distance;
    }

    public float getHeuristicDistance(){
        return this.distance + this.distanceToGoal;
    }

    public int getDistanceTo(Node other){
        int deltaX = this.x - other.x;
        int deltaY = this.y - other.y;
        return (deltaX*deltaX + deltaY*deltaY);
    }

    @Override
    public int compareTo(Node o) {
        return Float.compare(getHeuristicDistance(), o.getHeuristicDistance());
    }

    @Override
    public String toString() {
        return "Node (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Node){
            Node other = (Node) obj;
            return (x == other.x && y == other.y);
        }

        return super.equals(obj);
    }

    public void relax(Node parent, Node goal, PriorityQueue<Node> priorityQueue) {
        if (this.distance > parent.distance + parent.getDistanceTo(this)){
            this.distance = parent.distance + parent.getDistanceTo(this);
            this.parent = parent;
            this.distanceToGoal = this.getDistanceTo(goal);
            priorityQueue.remove(this);
            priorityQueue.add(this);
        }
    }
}
