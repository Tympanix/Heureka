package dtu.kursus02180.heureka.RouteFinding;

import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class RouteFinding {

    public static boolean runAStar(Graph graph, Node source, Node goal){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

        for (Map.Entry<Node, LinkedList<Edge>> entry : graph.incidenceList.entrySet()){
            priorityQueue.add(entry.getKey());
        }

        source.distance = 0;
        priorityQueue.add(source);


        while (!priorityQueue.isEmpty()){
            Node node = priorityQueue.poll();

            for (Edge edge : graph.getIncidenceList(node)){
                Node nextNode = edge.to;

                nextNode.relax(node, goal, priorityQueue);

                if (nextNode.equals(goal)) return true;
            }
        }

        return false;

    }

    public static void showPath(Node node){
        if (node == null) return;
        showPath(node.parent);
        System.out.println(node);
    }
}
