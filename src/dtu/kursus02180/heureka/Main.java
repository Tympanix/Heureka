package dtu.kursus02180.heureka;

import java.io.File;
import java.io.IOException;
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

        source.distance = 0;
        priorityQueue.add(source);


        while (!priorityQueue.isEmpty()){
            Node node = priorityQueue.poll();
            System.out.println(node);

            for (Edge edge : graph.getIncidenceList(node)){
                Node nextNode = edge.to;
                if (nextNode.isSeen()) continue;

                nextNode.setDistanceToGoal(goal);
                nextNode.parent = node;
                priorityQueue.add(nextNode);

                if (nextNode.equals(goal)) return true;
            }
        }

        return false;

    }

    public static void showPath(Node goal){
        Node parent = goal.parent;
        System.out.println(goal);

        while(parent != null){
            System.out.println(parent);
            parent = parent.parent;
        }
    }
}
