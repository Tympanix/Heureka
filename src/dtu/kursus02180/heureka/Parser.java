package dtu.kursus02180.heureka;

import java.io.*;
import java.util.StringTokenizer;

public class Parser {

    public static void parse(File file, Graph graph) throws IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        while(line != null){

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
