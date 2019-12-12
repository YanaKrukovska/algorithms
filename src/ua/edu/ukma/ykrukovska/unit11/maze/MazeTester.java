package ua.edu.ukma.ykrukovska.unit11.maze;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdRandom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class MazeTester {
    public static void main(String[] args) throws NumberFormatException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/IdeaProjects/Algorithms/src/ua/edu/ukma/ykrukovska/unit11/maze/test.txt"));
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            int vertexes = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int edges = Integer.parseInt(tokenizer.nextToken());
            Graph graph = new Graph(vertexes);
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                graph.addEdge(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

            int goal = StdRandom.uniform(vertexes - 1) + 1;

            DepthFirstPath dfs = new DepthFirstPath(graph, 0);
            BreadsFirstPath bfs = new BreadsFirstPath(graph, 0);
            System.out.println(graph.toString());
            System.out.println("Start vertex - 0");
            System.out.println("Goal vertex - " + goal);
            System.out.println("DFS: " + dfs.pathTo(goal).toString());
            System.out.println("DFS length = " + dfs.pathLength);
            System.out.println("BFS: " + bfs.pathTo(goal).toString());
            System.out.println("BFS length = " + bfs.getDistance(goal));
        } catch (NullPointerException e) {
            System.out.println("No path");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

