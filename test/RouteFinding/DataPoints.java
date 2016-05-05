package RouteFinding;

import dtu.kursus02180.heureka.RouteFinding.Graph;
import dtu.kursus02180.heureka.RouteFinding.Node;
import dtu.kursus02180.heureka.RouteFinding.Parser;
import dtu.kursus02180.heureka.RouteFinding.RouteFinding;

import java.io.File;
import java.io.IOException;

public class DataPoints {

    public static void main(String[] args) throws IOException {

        Graph graph = new Graph();

        File file = new File("data/DataPoints.txt");
        Parser.parse(file, graph);

        Node nodeSource = graph.findNode(10, 70);
        Node nodeGoal = graph.findNode(60, 150);

        System.out.println("Running algortihm!");
        RouteFinding.runAStar(graph, nodeSource, nodeGoal);

        System.out.println("Path:");
        RouteFinding.showPath(nodeGoal);

    }
}
