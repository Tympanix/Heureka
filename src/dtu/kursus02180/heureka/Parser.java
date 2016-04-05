package dtu.kursus02180.heureka;

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static void parse(File file, Graph graph) throws IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String regex = "[0-9]+ [0-9]+.+[0-9]+ [0-9]+";
        Pattern pattern = Pattern.compile(regex);

        String line = bufferedReader.readLine();
        while(line != null){

            Matcher m = pattern.matcher(line);

            if (!m.matches()){
                line = bufferedReader.readLine();
                continue;
            }

            StringTokenizer stringTokenizer = new StringTokenizer(line);

            int xFrom = Integer.parseInt(stringTokenizer.nextToken());
            int yFrom = Integer.parseInt(stringTokenizer.nextToken());

            String name = stringTokenizer.nextToken();

            int xTo = Integer.parseInt(stringTokenizer.nextToken());
            int yTo = Integer.parseInt(stringTokenizer.nextToken());

            Node nodeFrom = graph.findNode(xFrom, yFrom);
            if (nodeFrom == null) nodeFrom = new Node(xFrom, yFrom);

            Node nodeTo = graph.findNode(xTo, yTo);
            if (nodeTo == null) nodeTo = new Node(xTo, yTo);

            graph.addEdge(name, nodeFrom, nodeTo);


            line = bufferedReader.readLine();
        }

        fileReader.close();
        bufferedReader.close();

    }

}
