package Breakfast;

import dtu.kursus02180.heureka.InferenceEngine.InferenceEngine;

import java.io.File;

public class DirectProof {
    public static void main(String[] args) {
        File file = new File("data/breakfast.txt");
        String solve = "breakfast";
        InferenceEngine.runDirectProof(file, solve);
    }
}
