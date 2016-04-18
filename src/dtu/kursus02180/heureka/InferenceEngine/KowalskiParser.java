package dtu.kursus02180.heureka.InferenceEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class KowalskiParser {

    public static void parse(File file, KnowledgeBase knowledgeBase) throws IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        while(line != null){

            StringTokenizer stringTokenizer = new StringTokenizer(line);

            Clause clause = new Clause();
            boolean positiveLiterals = true;

            while (stringTokenizer.hasMoreTokens()){
                String inputLiteral = stringTokenizer.nextToken();

                if (inputLiteral.equals("if")){
                    positiveLiterals = false;
                    continue;
                }

                Literal literal = new Literal(inputLiteral, positiveLiterals);
                clause.addLiteral(literal);

            }

            knowledgeBase.addClause(clause);

            line = bufferedReader.readLine();
        }

        fileReader.close();
        bufferedReader.close();

    }

}
