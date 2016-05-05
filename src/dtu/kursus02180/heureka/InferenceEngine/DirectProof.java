package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Edge;
import dtu.kursus02180.heureka.Graph.Graph;
import dtu.kursus02180.heureka.Graph.Node;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.function.Function;

public class DirectProof {

    private KnowledgeBase knowledgeBase;
    private Graph graph;
    private HashSet<Clause> explored;
    private PriorityQueue<Node> priorityQueue;
    private Function<Clause, Float> heuristicsFunction;
    private Clause solve;

    public DirectProof(KnowledgeBase knowledgeBase){
        this.knowledgeBase = knowledgeBase;
        this.graph = new Graph();
        this.explored = new HashSet<>();
        this.priorityQueue = new PriorityQueue<>();
        this.heuristicsFunction = this::heuristicsFunction;
    }

    public float heuristicsFunction(Clause clause){
        return clause.getDistance() + 2 * clause.getLiteralsNotIn(this.solve);
    }

    public Clause run(Clause solve){

        this.solve = solve;

        for (Clause rule : knowledgeBase.list){
            rule.setDistance(0);
            rule.heuristicFunction = heuristicsFunction;
            priorityQueue.add(rule);
        }

        while (!priorityQueue.isEmpty()){
            Clause clause = (Clause) priorityQueue.poll();

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
