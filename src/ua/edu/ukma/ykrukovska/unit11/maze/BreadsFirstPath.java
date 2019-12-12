package ua.edu.ukma.ykrukovska.unit11.maze;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadsFirstPath {
    private final int start;
    private boolean[] visited;
    private int[] edgeTo;
    private int[] distanceTo;

    public BreadsFirstPath(Graph G, int start) {
        this.start = start;
        visited = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distanceTo = new int[G.V()];
        bfs(G);
    }

    private void bfs(Graph G) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(start);
        visited[start] = true;
        distanceTo[start] = 0;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.enqueue(w);
                    visited[w] = true;
                    edgeTo[w] = v;
                    distanceTo[w] = distanceTo[v] + 1;
                }
            }
        }
    }

    private boolean hasPathTo(int v) {
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != start; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(start);
        return path;
    }

    public int getDistance(int v) {
        return distanceTo[v];
    }
}