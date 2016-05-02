package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Graph;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        KnowledgeBase knowledgeBase = new KnowledgeBase();

        File file = new File("espressomachine.txt");
        KowalskiParser.parse(file, knowledgeBase);

        System.out.println(knowledgeBase);

        Clause solve = new Clause();
        solve.addLiteral(new Literal("hot-drink", true));

        Graph graph = new Graph();

        Clause solved = InferenceEngine.indirectProof(knowledgeBase, solve, graph);

        System.out.println("SHOW PATH!");

        InferenceEngine.showPath(solved);

    }
}
