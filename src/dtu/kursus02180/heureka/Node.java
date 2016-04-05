package dtu.kursus02180.heureka;


public class Node implements Comparable<Node> {

    float distance;
    Node parent;
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.parent = null;
        this.distance = Float.MAX_VALUE;
    }

    public boolean isSeen(){
        return distance != Float.MAX_VALUE;
    }

    public void setDistanceToGoal(Node goal){
        distance = getDistance(goal);
    }

    public int getDistance(Node other){
        int deltaX = this.x - other.x;
        int deltaY = this.y - other.y;
        return (deltaX*deltaX + deltaY*deltaY);
    }

    @Override
    public int compareTo(Node o) {
        return Float.compare(distance, o.distance);
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
}
