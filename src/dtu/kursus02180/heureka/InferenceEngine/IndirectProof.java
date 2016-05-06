package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Edge;
import dtu.kursus02180.heureka.Graph.Graph;
import dtu.kursus02180.heureka.InferenceEngine.Heuristics.HeuristicIndirect;

import java.util.HashSet;
import java.util.PriorityQueue;

public class IndirectProof {

    private Graph graph;
    private KnowledgeBase knowledgeBase;
    private PriorityQueue<Clause> priorityQueue;
    private HashSet<Clause> explored = new HashSet<>();

    public IndirectProof(KnowledgeBase knowledgeBase){
        this.graph = new Graph();
        this.knowledgeBase = knowledgeBase;
        this.priorityQueue = new PriorityQueue<>(new HeuristicIndirect());
        this.explored = new HashSet<>();
    }

    public Clause run(Clause solveOriginal){

        // Negate solving clause
        Clause solve = new Clause();
        solve.conclusion.addAll(solveOriginal.premise);
        solve.premise.addAll(solveOriginal.conclusion);

        graph.addNode(solve);

        solve.setDistance(0);
        priorityQueue.add(solve);
        explored.add(solve);

        while (!priorityQueue.isEmpty()){
            Clause clause = priorityQueue.poll();

            for (Clause rule : knowledgeBase.list){

                Clause resolventClause = resolveClause(clause, rule);
                if (resolventClause != null) return resolventClause;

            }

            Clause resolventClause = ancestorResolution(clause, clause.getParent());
            if (resolventClause != null) return resolventClause;

        }

        return null;
    }

    public Clause resolveClause(Clause clause, Clause rule) {
        Clause resolventClause = clause.applyRule(rule);

        if (resolventClause == null){
            return null;
        }

        if (!explored.add(resolventClause)){
            return null;
        }

        graph.addNode(resolventClause);
        Edge newEdge = graph.addEdge(clause, resolventClause, 1);

        resolventClause.relax(clause, newEdge, priorityQueue);

        if (resolventClause.isEmptyClause()){
            return resolventClause;
        }

        return null;
    }

    public Clause ancestorResolution(Clause clause, Clause parentClause){
        if (parentClause == null) return null;
        Clause resolventClause = resolveClause(clause, parentClause);
        if (resolventClause != null) return resolventClause;
        return ancestorResolution(clause, parentClause.getParent());
    }
}
