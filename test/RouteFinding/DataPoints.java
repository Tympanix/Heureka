package RouteFinding;

import dtu.kursus02180.heureka.RouteFinding.CityMap;
import dtu.kursus02180.heureka.RouteFinding.Crossing;
import dtu.kursus02180.heureka.RouteFinding.Parser;
import dtu.kursus02180.heureka.RouteFinding.RouteFinding;

import java.io.File;
import java.io.IOException;

public class DataPoints {

    public static void main(String[] args) throws IOException {

        CityMap graph = new CityMap();

        File file = new File("data/DataPoints.txt");
        Parser.parse(file, graph);

        Crossing nodeSource = graph.findNode(10, 70);
        Crossing nodeGoal = graph.findNode(60, 150);

        System.out.println("Running algortihm!");
        RouteFinding.runAStar(graph, nodeSource, nodeGoal);

        System.out.println("Path:");
        RouteFinding.showPath(nodeGoal);

    }
}
