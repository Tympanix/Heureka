package dtu.kursus02180.heureka.RouteFinding;

import dtu.kursus02180.heureka.Graph.Edge;
import dtu.kursus02180.heureka.Graph.Node;

import java.util.PriorityQueue;

public class RouteFinding {

    public static boolean runAStar(CityMap graph, Crossing source, Crossing goal){
        // Create a priority queue with a sorting algorithm (evaluation function)
        PriorityQueue<Crossing> priorityQueue = new PriorityQueue<>(new HeuristicAStar(goal));

        source.distance = 0;
        priorityQueue.add(source);


        while (!priorityQueue.isEmpty()){
            Crossing node = priorityQueue.poll();

            for (Edge edge : graph.getIncidenceList(node)){
                Node nextNode = edge.to;

                nextNode.relax(node, edge, priorityQueue);

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
