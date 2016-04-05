package dtu.kursus02180.heureka;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        Graph graph = new Graph();

        File file = new File("manhattan.txt");
        Parser.parse(file, graph);

        Node nodeSource = graph.findNode(0, 0);
        Node nodeGoal = graph.findNode(5, 5);

        System.out.println("Running algortihm!");
        runAStar(graph, nodeSource, nodeGoal);

        System.out.println("Path:");
        showPath(nodeGoal);

    }


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
//                priorityQueue.add(nextNode);

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
