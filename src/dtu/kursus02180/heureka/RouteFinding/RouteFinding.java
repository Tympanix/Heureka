package dtu.kursus02180.heureka.RouteFinding;

import java.util.PriorityQueue;

public class RouteFinding {

    public static boolean runAStar(Graph graph, Node source, Node goal){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

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
