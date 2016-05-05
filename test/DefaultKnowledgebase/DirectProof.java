package DefaultKnowledgebase;

import dtu.kursus02180.heureka.InferenceEngine.InferenceEngine;

import java.io.File;

public class DirectProof {
    public static void main(String[] args) {
        File file = new File("data/knowledgebase.txt");
        String solve = "a";
        InferenceEngine.runDirectProof(file, solve);
    }
}
