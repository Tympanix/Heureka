package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Graph;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        KnowledgeBase knowledgeBase = new KnowledgeBase();

        File file = new File("knowledgebase.txt");
        KowalskiParser.parse(file, knowledgeBase);

        Clause solve = new Clause();
        solve.addLiteral(new Literal("a", true));

        Graph graph = new Graph();

        Clause solved = InferenceEngine.solve(knowledgeBase, solve, graph);

        System.out.println("SHOW PATH!");

        InferenceEngine.showPath(solved);
        //graph.showPath(solved);

    }
}
