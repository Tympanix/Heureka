package dtu.kursus02180.heureka.Graph;

public class Edge {

    Node from;
    Node to;

    public int getWeight() {
        return weight;
    }

    int weight;

    public Edge(Node from, Node to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

}
