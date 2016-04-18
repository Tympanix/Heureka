package dtu.kursus02180.heureka.InferenceEngine;

import java.util.ArrayList;

public class KnowledgeBase {

    ArrayList<Clause> list = new ArrayList<Clause>();

    public KnowledgeBase(){

    }

    public void addClause(Clause clause){
        list.add(clause);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Clause clause : list){
            stringBuilder.append(clause).append("\n");
        }

        return stringBuilder.toString();
    }
}
