package EspressoMachine;

import dtu.kursus02180.heureka.InferenceEngine.InferenceEngine;
import dtu.kursus02180.heureka.InferenceEngine.KnowledgeBase;
import dtu.kursus02180.heureka.InferenceEngine.KowalskiParser;
import dtu.kursus02180.heureka.InferenceEngine.Literal;

import java.io.File;
import java.io.IOException;

public class DirectProof {

    public static void main(String[] args) throws IOException {
        File file = new File("data/espressomachine.txt");
        String solve = "hot-drink";
        InferenceEngine.runDirectProof(file, solve);
    }

}
