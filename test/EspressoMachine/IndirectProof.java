package EspressoMachine;

import dtu.kursus02180.heureka.InferenceEngine.InferenceEngine;

import java.io.File;
import java.io.IOException;

public class IndirectProof {

    public static void main(String[] args) throws IOException {
        File knowledgebase = new File("data/espressomachine.txt");
        String solve = "hot-drink";
        InferenceEngine.runIndirectProof(knowledgebase, solve);
    }

}
