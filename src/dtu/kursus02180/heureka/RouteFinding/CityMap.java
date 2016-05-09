package dtu.kursus02180.heureka.RouteFinding;

import dtu.kursus02180.heureka.Graph.Edge;
import dtu.kursus02180.heureka.Graph.Graph;
import dtu.kursus02180.heureka.Graph.Node;

import java.util.LinkedList;
import java.util.Map;

public class CityMap extends Graph {

    public CityMap() {
        super();
    }

    public Crossing findNode(int x, int y){
        for (Map.Entry<Node, LinkedList<Edge>> entry : incidenceList.entrySet()){
            Crossing node = (Crossing) entry.getKey();
            if (node.equals(new Crossing(x, y))){
                return node;
            }
        }

        return null;

    }

    public Edge addEdge(String name, Crossing from, Crossing to){
        LinkedList<Edge> list = incidenceList.get(from);

        if (list == null){
            addNode(from);
            list = incidenceList.get(from);
        }

        if (!incidenceList.containsKey(to)){
            addNode(to);
        }

        Edge edge = new Street(name, from, to);
        list.add(edge);

        return edge;
    }

}
