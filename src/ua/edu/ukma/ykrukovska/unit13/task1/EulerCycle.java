package ua.edu.ukma.ykrukovska.unit13.task1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import ua.edu.ukma.ykrukovska.unit3.collections.ArrayStack;

import java.util.Iterator;

public class EulerCycle {
    private static ArrayStack<Integer> cycle = new ArrayStack<>();
    private static final String testFile = "C:/IdeaProjects/Algorithms/src/ua/edu/ukma/ykrukovska/unit13/task1.txt";
    private boolean isEulerian = true;

    public EulerCycle(Graph G) {

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
        ArrayStack<Integer> stack = new ArrayStack<>();
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
                isEulerian = false;
            }
        }
        for (int v = 0; v < G.V(); v++) {
            if (adj[v].hasNext()) {
                isEulerian = false;
            }
        }
    }


    private boolean hasEulerian() {
        return isEulerian;
    }

    public static void main(String[] args) {
        In in = new In(testFile);
        Graph G = new Graph(in);
        EulerCycle eulerCycle = new EulerCycle(G);
        System.out.println("Euler cycle in graph: " + eulerCycle.isEulerian);
        if (eulerCycle.hasEulerian()) {
            while (!cycle.isEmpty()) {
                System.out.print(cycle.pop() + " -> ");

            }

        }
    }
}