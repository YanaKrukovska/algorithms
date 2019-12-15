package ua.edu.ukma.ykrukovska.unit13.task1;

import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.util.Stack;

public class HamiltonCycle {
    private static String cycleString = "";
    private static Graph G;
    private static final String testFile = "C:/IdeaProjects/Algorithms/src/ua/edu/ukma/ykrukovska/unit13/task1.txt";
    private boolean[] marked;
    private Stack<Integer> vertexStack;

    public HamiltonCycle(Graph G) {
        vertexStack = new Stack<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int vertice : G.adj(v)) {
            if (!marked[vertice]) {
                dfs(G, vertice);
            }
        }
        vertexStack.push(v);
    }


    private boolean isHamilton() {
        int vertex = vertexStack.pop();
        cycleString += vertex + " -> ";
        while (!vertexStack.isEmpty()) {
            int v = vertexStack.pop();
            cycleString += v + " -> ";
            DepthFirstPaths dfs = new DepthFirstPaths(G, vertex);
            if (!dfs.hasPathTo(v)) {
                return false;
            }
            vertex = v;
        }
        cycleString += 0;
        return true;
    }

    public static void main(String[] args) {
        In in = new In(testFile);
        G = new Graph(in);
        HamiltonCycle hc = new HamiltonCycle(G);
        boolean isHamilton = hc.isHamilton();
        System.out.println("Hamilton cycle: " + isHamilton);
        if (isHamilton) {
            System.out.println(cycleString);
        }
    }


}
