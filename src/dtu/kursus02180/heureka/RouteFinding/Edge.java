package dtu.kursus02180.heureka.RouteFinding;

public class Edge {

    String name;
    Node from;
    Node to;

    public Edge(String name, Node from, Node to){
        this.name = name;
        this.from = from;
        this.to = to;
    }

}
