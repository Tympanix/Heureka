package dtu.kursus02180.heureka.Graph;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    public HashMap<Node, LinkedList<Edge>> incidenceList;

    public Graph(){
        incidenceList = new HashMap<>();
    }

    public void addNode(Node node){
        incidenceList.put(node, new LinkedList<Edge>());
    }

    public LinkedList<Edge> getIncidenceList(Node node){
        return incidenceList.get(node);
    }

    public Edge addEdge(Node from, Node to, int weight){
        LinkedList<Edge> list = incidenceList.get(from);

        if (list == null){
            addNode(from);
            list = incidenceList.get(from);
        }

        if (!incidenceList.containsKey(to)){
            addNode(to);
        }

        Edge edge = new Edge(from, to, weight);
        list.add(edge);

        return edge;
    }

    public void showPath(Node node){
        if (node == null) return;
        showPath(node.parent);
        System.out.println(node);
    }


}
