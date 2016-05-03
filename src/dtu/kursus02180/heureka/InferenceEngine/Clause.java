package dtu.kursus02180.heureka.InferenceEngine;

import dtu.kursus02180.heureka.Graph.Node;

import java.util.*;

public class Clause extends Node {

    HashSet<Literal> conclusion = new HashSet<Literal>();
    HashSet<Literal> premise = new HashSet<Literal>();

    Clause ruleUsed = null;

    public Clause(){
        super();
    }

    public int getPremiseLength(){
        return premise.size();
    }

    public boolean isKnownFact(){
        return conclusion.size() == 1 && premise.isEmpty();
    }

    public Literal getKnownFact(){
        if (isKnownFact()){
            Iterator<Literal> iterator = conclusion.iterator();
            return iterator.next();
        } else {
            return null;
        }
    }


    @Override
    public Clause getParent() {
        return (Clause) super.getParent();
    }

    public float getClauseLength(){
        return conclusion.size() + premise.size();
    }

    @Override
    public float getHeuristicDistance() {
        return this.getDistance() + 2*this.getClauseLength();
    }

    public void addLiteral(Literal literal){
        if (literal.isPositive){
            conclusion.add(literal);
        } else {
            premise.add(literal);
        }
    }

    public void removeLiteral(Literal literal){
        if (literal.isPositive){
            conclusion.remove(literal);
        } else {
            premise.remove(literal);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (isEmptyClause()){
            stringBuilder.append("<Empty>");
            return stringBuilder.toString();
        }

        for (Literal l : conclusion){
            stringBuilder.append(l.name).append(" ");
        }

        if (premise.isEmpty()){
            return stringBuilder.toString();
        }

        stringBuilder.append("if ");

        for (Literal l : premise){
            stringBuilder.append(l.name).append(" ");
        }


        return stringBuilder.toString();
    }

    public Clause applyRule(Clause rule) {
        Clause resultClause = new Clause();

        // Set the new clause to have been deducted by this rule
        resultClause.ruleUsed = rule;

        // Clone this clause
        resultClause.conclusion.addAll(this.conclusion);
        resultClause.premise.addAll(this.premise);

        // Add literals from rule
        resultClause.conclusion.addAll(rule.conclusion);
        resultClause.premise.addAll(rule.premise);

        // Remove contradicting literals
        if (!resultClause.removeContradicting()) return null;

        return resultClause;
    }

    private boolean removeContradicting() {

        Iterator<Literal> iterator = conclusion.iterator();
        boolean alreadyRemoved = false;

        while(iterator.hasNext()){
            Literal literal = iterator.next();
            if (premise.contains(literal)){
                iterator.remove();
                premise.remove(literal);
                if (alreadyRemoved) return false;
                alreadyRemoved = true;
            }
        }
        return true;
    }

    public boolean isEmptyClause() {
        return conclusion.isEmpty() && premise.isEmpty();
    }

    public String getUniqueIdentifier(){
        StringBuilder stringBuilder = new StringBuilder();

        TreeSet<Literal> positiveSorted = new TreeSet<Literal>(conclusion);

        for (Literal literal : positiveSorted){
            stringBuilder.append(literal.name);
        }

        stringBuilder.append(":");

        TreeSet<Literal> negativeSorted = new TreeSet<Literal>(premise);

        for (Literal literal : negativeSorted){
            stringBuilder.append(literal.name);
        }

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return getUniqueIdentifier().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Clause){
            Clause clause = (Clause) obj;
            return (conclusion.equals(clause.conclusion) &&
                    premise.equals(clause.premise));
        } else {
            return super.equals(obj);
        }
    }
}
