package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Edge;
import dtu.kursus02180.heureka.Graph.Graph;
import dtu.kursus02180.heureka.Graph.Node;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class InferenceEngine {

    public static void runIndirectProof(File knowledgeFile, String solveLiteral){
        KnowledgeBase knowledgeBase = new KnowledgeBase();

        try {
            KowalskiParser.parse(knowledgeFile, knowledgeBase);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Clause solve = new Clause();
        solve.addLiteral(new Literal(solveLiteral, true));

        Graph graph = new Graph();

        Clause solved = indirectProof(knowledgeBase, solve, graph);

        showPath(solved);
    }

    public static void runDirectProof(File knowledgeFile, String solveLiteral){
        KnowledgeBase knowledgeBase = new KnowledgeBase();

        try {
            KowalskiParser.parse(knowledgeFile, knowledgeBase);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Literal solve = new Literal(solveLiteral, true);

        boolean solved = InferenceEngine.directProof(knowledgeBase, solve);

        System.out.println("Solved: " + solved);

    }

    public static Clause indirectProof(KnowledgeBase knowledgeBase, Clause solveOriginal, Graph graph){

        HashSet<Clause> explored = new HashSet<Clause>();

        // Negate solving clause
        Clause solve = new Clause();
        solve.conclusion.addAll(solveOriginal.premise);
        solve.premise.addAll(solveOriginal.conclusion);

        graph.addNode(solve);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        solve.setDistance(0);
        priorityQueue.add(solve);
        explored.add(solve);

        while (!priorityQueue.isEmpty()){
            Clause clause = (Clause) priorityQueue.poll();
            System.out.println("Chose: " + clause);

            for (Clause rule : knowledgeBase.list){

                Clause resolventClause = clause.applyRule(rule);

                if (resolventClause == null){
                    System.out.println("Error!");
                    continue;
                }

                if (!explored.add(resolventClause)){
                    System.out.println("Nitte!");
                    continue;
                }

                graph.addNode(resolventClause);
                Edge newEdge = graph.addEdge(clause, resolventClause, 1);

                resolventClause.relax(clause, newEdge, priorityQueue);

                if (resolventClause.isEmptyClause()){
                    return resolventClause;
                }
            }

            System.out.println();

        }

        return null;
    }

    public static void showPath(Clause node){
        if (node == null) return;
        showPath((Clause) node.getParent());
        System.out.format("%-37sRule: %-37s\n", node, node.ruleUsed);
    }

    public static boolean directProof(KnowledgeBase knowledgeBase, Literal q){

        // Length premise for all clauses in KB
        HashMap<Clause, Integer> count = new HashMap<Clause, Integer>();
        for (Clause clause : knowledgeBase.list){
            count.put(clause, clause.getPremiseLength());
        }

        // List of already proven literals
        HashSet<Literal> inferred = new HashSet<Literal>();

        // All initially known facts
        LinkedList<Literal> agenda = new LinkedList<Literal>();
        for (Clause clause : knowledgeBase.list){
            Literal knowFact = clause.getKnownFact();
            if (knowFact != null) agenda.add(knowFact);
        }

        while (!agenda.isEmpty()){
            Literal p = agenda.pop();
            if (p.equals(q)) return true;
            if (!inferred.contains(p)){
                inferred.add(p);
                for (Clause clause : knowledgeBase.list){
                    if (!clause.premise.contains(p)) continue;
                    count.put(clause, count.get(clause) - 1);
                    if (count.get(clause) == 0){
                        agenda.addAll(clause.conclusion);
                    }
                }
            }
        }

        return false;
    }

}
