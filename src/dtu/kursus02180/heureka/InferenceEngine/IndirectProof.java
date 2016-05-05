package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Edge;
import dtu.kursus02180.heureka.Graph.Graph;
import dtu.kursus02180.heureka.Graph.Node;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.function.Function;

public class IndirectProof {

    private Graph graph;
    private KnowledgeBase knowledgeBase;
    private PriorityQueue<Node> priorityQueue;
    private HashSet<Clause> explored = new HashSet<>();
    private Function<Clause, Float> heuristicsFunction;

    public IndirectProof(KnowledgeBase knowledgeBase){
        this.graph = new Graph();
        this.knowledgeBase = knowledgeBase;
        this.priorityQueue = new PriorityQueue<>();
        this.explored = new HashSet<>();
        this.heuristicsFunction = this::heuristicsFunction;
    }

    public float heuristicsFunction(Clause clause){
        return clause.getDistance() + 2 * clause.getClauseLength();
    }

    public Clause run(Clause solveOriginal){

        // Negate solving clause
        Clause solve = new Clause(heuristicsFunction);
        solve.conclusion.addAll(solveOriginal.premise);
        solve.premise.addAll(solveOriginal.conclusion);

        graph.addNode(solve);

        solve.setDistance(0);
        solve.heuristicFunction = this.heuristicsFunction;
        priorityQueue.add(solve);
        explored.add(solve);

        while (!priorityQueue.isEmpty()){
            Clause clause = (Clause) priorityQueue.poll();

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
