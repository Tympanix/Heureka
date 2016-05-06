package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Edge;
import dtu.kursus02180.heureka.Graph.Graph;
import dtu.kursus02180.heureka.InferenceEngine.Heuristics.HeuristicDirect;

import java.util.HashSet;
import java.util.PriorityQueue;

public class DirectProof {

    private KnowledgeBase knowledgeBase;
    private Graph graph;
    private HashSet<Clause> explored;
    private PriorityQueue<Clause> priorityQueue;
    private Clause solve;

    public DirectProof(KnowledgeBase knowledgeBase){
        this.knowledgeBase = knowledgeBase;
        this.graph = new Graph();
        this.explored = new HashSet<>();
    }


    public Clause run(Clause solve){

        this.solve = solve;
        this.priorityQueue = new PriorityQueue<>(new HeuristicDirect(this.solve));

        for (Clause rule : knowledgeBase.list){
            rule.setDistance(0);
            priorityQueue.add(rule);
            explored.add(rule);
        }

        while (!priorityQueue.isEmpty()){
            Clause clause = priorityQueue.poll();

            for (Clause rule : knowledgeBase.list){

                Clause resolventClause = resolveClause(clause, rule, solve);
                if (resolventClause != null) return resolventClause;

            }

            Clause resolventClause = ancestorResolution(clause, clause.getParent(), solve);
            if (resolventClause != null) return resolventClause;
        }

        return null;

    }


    public Clause resolveClause(Clause clause, Clause rule, Clause solve) {
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

        if (resolventClause.equals(solve)){
            return resolventClause;
        }

        return null;
    }

    public Clause ancestorResolution(Clause clause, Clause parentClause, Clause solve){
        if (parentClause == null) return null;
        Clause resolventClause = resolveClause(clause, parentClause, solve);
        if (resolventClause != null) return resolventClause;
        return ancestorResolution(clause, parentClause.getParent(), solve);
    }


}
