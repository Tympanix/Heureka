package dtu.kursus02180.heureka.InferenceEngine;

import java.io.File;
import java.io.IOException;

public class InferenceEngine {

    public static void runIndirectProof(File knowledgeFile, String solveLiteral){
        KnowledgeBase knowledgeBase = new KnowledgeBase();

        try {
            KowalskiParser.parse(knowledgeFile, knowledgeBase);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Clause solve = new Clause();
        solve.addLiteral(new Literal(solveLiteral, true));

        IndirectProof indirectProof = new IndirectProof(knowledgeBase);
        Clause solved = indirectProof.run(solve);

        System.out.println("Path:");
        showPath(solved);
    }

    public static void runDirectProof(File knowledgeFile, String solveLiteral){
        KnowledgeBase knowledgeBase = new KnowledgeBase();

        try {
            KowalskiParser.parse(knowledgeFile, knowledgeBase);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Clause solve = new Clause();
        solve.addLiteral(new Literal(solveLiteral, true));

        DirectProof directProof = new DirectProof(knowledgeBase);
        Clause solved = directProof.run(solve);

        System.out.println("Path:");
        showPath(solved);

    }

    public static void showPath(Clause node){
        if (node == null) return;
        showPath(node.getParent());
        System.out.format("%-52sRule: %-52s\n", node, node.ruleUsed);
    }

}
