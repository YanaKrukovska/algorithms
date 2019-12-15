package ua.edu.ukma.ykrukovska.unit13.task4;


import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import ua.edu.ukma.ykrukovska.unit13.task3.Degrees;
import ua.edu.ukma.ykrukovska.unit3.collections.ArrayStack;

import java.util.Iterator;

public class EulerCycle {
    private static final String testFile = "C:/IdeaProjects/Algorithms/src/ua/edu/ukma/ykrukovska/unit13/task1.txt";
    private static ArrayStack<Integer> cycle = new ArrayStack<Integer>();
    private boolean isEul = true;

    public EulerCycle(Digraph G) {
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }
        int s = 0;
        for (int v = 0; v < G.V(); v++) {
            if (adj[v].hasNext()) {
                s = v;
                break;
            }
        }
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            cycle.push(v);
            int w = v;
            while (adj[w].hasNext()) {
                stack.push(w);
                w = adj[w].next();
            }
            if (w != v) {
                isEul = false;
            }
        }
        for (int v = 0; v < G.V(); v++) {
            if (adj[v].hasNext()) {
                isEul = false;
            }
        }
    }


    public boolean isEulerian(Digraph digraph) {

        Degrees degrees = new Degrees(digraph);
        for (int i = 0; i < digraph.V(); i++) {
            if (degrees.indegree(i) != degrees.outdegree(i)) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        In in = new In(testFile);
        Digraph G = new Digraph(in);
        EulerCycle eulerCycle = new EulerCycle(G);
        System.out.println("Euler cycle in graph:" + eulerCycle.isEul);
        if (eulerCycle.isEulerian(G)) {
            while (!cycle.isEmpty()) {
                System.out.print(cycle.pop() + " -> ");
            }
        }
    }
}
