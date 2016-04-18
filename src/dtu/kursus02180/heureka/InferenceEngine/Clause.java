package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Node;

import java.util.*;

public class Clause extends Node {

    HashSet<Literal> positiveLiterals = new HashSet<Literal>();
    HashSet<Literal> negativeLiterals = new HashSet<Literal>();

    public Clause(){
        super();
    }

    @Override
    public float getDistanceTo(Node goal) {
        return positiveLiterals.size() + negativeLiterals.size();
    }

    public void addLiteral(Literal literal){
        if (literal.isPositive){
            positiveLiterals.add(literal);
        } else {
            negativeLiterals.add(literal);
        }
    }

    public void removeLiteral(Literal literal){
        if (literal.isPositive){
            positiveLiterals.remove(literal);
        } else {
            negativeLiterals.remove(literal);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Literal l : positiveLiterals){
            stringBuilder.append(l.name).append(" ");
        }

        if (negativeLiterals.isEmpty()){
            return stringBuilder.toString();
        }

        stringBuilder.append("if ");

        for (Literal l : negativeLiterals){
            stringBuilder.append(l.name).append(" ");
        }


        return stringBuilder.toString();
    }

    public Clause applyRule(Clause rule) {
        Clause resultClause = new Clause();

        // Clone this clause
        resultClause.positiveLiterals.addAll(this.positiveLiterals);
        resultClause.negativeLiterals.addAll(this.negativeLiterals);

        System.out.println("Copy: " + resultClause);
        System.out.println("Rule: " + rule);

        // Add literals from rule
        resultClause.positiveLiterals.addAll(rule.positiveLiterals);
        resultClause.negativeLiterals.addAll(rule.negativeLiterals);

        System.out.println("Merged: " + resultClause);

        // Remove contradicting literals
        resultClause.removeContradicting();

        System.out.println("Result: " + resultClause);
        System.out.println();

        return resultClause;
    }

    private void removeContradicting() {

        Iterator<Literal> iterator = positiveLiterals.iterator();

        while(iterator.hasNext()){
            Literal literal = iterator.next();
            if (negativeLiterals.contains(literal)){
                iterator.remove();
                negativeLiterals.remove(literal);
            }
        }
    }

    public boolean isEmptyClause() {
        return positiveLiterals.isEmpty() && negativeLiterals.isEmpty();
    }

    public String getUniqueIdentifier(){
        StringBuilder stringBuilder = new StringBuilder();

        TreeSet<Literal> positiveSorted = new TreeSet<Literal>(positiveLiterals);

        for (Literal literal : positiveSorted){
            stringBuilder.append(literal.name);
        }

        stringBuilder.append(":");

        TreeSet<Literal> negativeSorted = new TreeSet<Literal>(negativeLiterals);

        for (Literal literal : negativeSorted){
            stringBuilder.append(literal.name);
        }

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return getUniqueIdentifier().hashCode();
    }
}
