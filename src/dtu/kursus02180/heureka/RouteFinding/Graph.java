package dtu.kursus02180.heureka.RouteFinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {

    HashMap<Node, LinkedList<Edge>> incidenceList;

    public Graph(){
        incidenceList = new HashMap<Node, LinkedList<Edge>>();
    }

    public void addNode(Node node){
        incidenceList.put(node, new LinkedList<Edge>());
    }

    public LinkedList<Edge> getIncidenceList(Node node){
        return incidenceList.get(node);
    }

    public void addEdge(String name, Node from, Node to){
        LinkedList<Edge> list = incidenceList.get(from);

        if (list == null){
            addNode(from);
            list = incidenceList.get(from);
        }

        if (!incidenceList.containsKey(to)){
            addNode(to);
        }

        list.add(new Edge(name, from, to));
    }

    public Node findNode(int x, int y){
        for (Map.Entry<Node, LinkedList<Edge>> entry : incidenceList.entrySet()){
            Node node = entry.getKey();
            if (node.equals(new Node(x, y))){
                return node;
            }
        }

        return null;

    }

}
