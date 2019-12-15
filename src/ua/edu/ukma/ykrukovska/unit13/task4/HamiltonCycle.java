package ua.edu.ukma.ykrukovska.unit13.task4;


import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Stack;


public class HamiltonCycle {
    private static String cyclePrint = "";
    private static Digraph digraph;
    private static final String testFile = "C:/IdeaProjects/Algorithms/src/ua/edu/ukma/ykrukovska/unit13/task1.txt";
    private boolean[] marked;
    private Stack<Integer> stack;

    private HamiltonCycle(Digraph digraph) {
        stack = new Stack<>();
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
        stack.push(v);
    }

    private boolean isHamilton() {
        int w = stack.pop();
        cyclePrint += w + " -> ";
        while (!stack.isEmpty()) {
            int v = stack.pop();
            cyclePrint += v + " -> ";
            DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(digraph, w);
            if (!dfs.hasPathTo(v)) {
                return false;
            }
            w = v;
        }
        cyclePrint += 0;
        return true;
    }


    public static void main(String[] args) {
        In in = new In(testFile);
        digraph = new Digraph(in);
        HamiltonCycle hamiltonCycle = new HamiltonCycle(digraph);
        boolean hasHamilton = hamiltonCycle.isHamilton();
        System.out.println("Hamilton cycle: " + hasHamilton);
        if (hasHamilton) {
            System.out.println(cyclePrint);
        }
    }

}
