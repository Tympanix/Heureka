package Breakfast;

import dtu.kursus02180.heureka.InferenceEngine.InferenceEngine;

import java.io.File;
import java.io.IOException;

public class IndirectProof {
    public static void main(String[] args) throws IOException {
        File knowledgebase = new File("data/breakfast.txt");
        String solve = "breakfast";
        InferenceEngine.runIndirectProof(knowledgebase, solve);
    }
}
