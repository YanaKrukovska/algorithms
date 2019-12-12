package ua.edu.ukma.ykrukovska.unit11.maze;

import edu.princeton.cs.algs4.*;

public class DepthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private final int start;
    public static int pathLength = 0;

    public DepthFirstPath(Graph G, int start) {
        this.start = start;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(start);
        dfs(G, start);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    private boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != start; x = edgeTo[x]) {
            path.push(x);
            pathLength++;
        }
        path.push(start);
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}
