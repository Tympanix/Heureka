package dtu.kursus02180.heureka.InferenceEngine;

public class Literal implements Comparable<Literal> {

    String name;
    boolean isPositive;

    public Literal(String name, boolean positiveLiteral){
        this.name = name;
        this.isPositive = positiveLiteral;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Literal){
            Literal literal = (Literal) obj;
            return this.name.equals(literal.name);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Literal o) {
        return name.compareTo(o.name);
    }
}
