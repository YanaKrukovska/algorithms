package ua.edu.ukma.ykrukovska.unit12.wordNet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class SAP {

    private final Digraph digraph;
    private static final String FILE_PATH = "D:/Studying/Algorithms/Practice12/";

    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        this.digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {

        if (!isValidVertex(v) || !isValidVertex(w)) {
            throw new IllegalArgumentException();
        }

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int ancestor = ancestor(v, w);

        return (ancestor == -1) ? -1 : bfsV.distTo(ancestor) + bfsW.distTo(ancestor);

    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {

        if (!isValidVertex(v) || !isValidVertex(w)) {
            throw new IllegalArgumentException();
        }
        int shortestPath = Integer.MAX_VALUE;
        ArrayList<Integer> ancestors = new ArrayList<Integer>();
        int ancestor = -1;

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        for (int i = 0; i < digraph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                ancestors.add(i);
            }
        }

        for (Integer x : ancestors) {
            if ((bfsV.distTo(x) + bfsW.distTo(x)) < shortestPath) {
                shortestPath = (bfsV.distTo(x) + bfsW.distTo(x));
                ancestor = x;
            }
        }
        return ancestor;


    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        if (!areValidVertices(v) || !areValidVertices(w)) {
            throw new IllegalArgumentException();
        }

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        int ancestor = ancestor(v, w);

        return (ancestor == -1) ? -1 : bfsV.distTo(ancestor) + bfsW.distTo(ancestor);


    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        if (!areValidVertices(v) || !areValidVertices(w)) {
            throw new IllegalArgumentException();
        }

        int shortestPath = Integer.MAX_VALUE;
        ArrayList<Integer> ancestors = new ArrayList<Integer>();
        int ancestor = -1;

        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

        for (int i = 0; i < digraph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                ancestors.add(i);
            }
        }

        for (Integer x : ancestors) {
            if ((bfsV.distTo(x) + bfsW.distTo(x)) < shortestPath) {
                shortestPath = (bfsV.distTo(x) + bfsW.distTo(x));
                ancestor = x;
            }
        }
        return ancestor;
    }

    private boolean isValidVertex(int i) {
        return i >= 0 && i < digraph.V();
    }

    private boolean areValidVertices(Iterable<Integer> v) {

        if (v == null) {
            return false;
        }

        for (Integer x : v) {
            if (x == null || !isValidVertex(x)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        In in = new In("D:/Studying/Algorithms/Practice12/digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        In in2 = new In("D:/Studying/Algorithms/Practice12/digraph1.txt");

        int a = in2.readInt();
        int b = in2.readInt();
        while (!in2.isEmpty()) {
            int v = in2.readInt();
            int w = in2.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            System.out.println("v = " + v + ", w = " + w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }

        System.out.println();
        //  Expected 4 / 1
        StdOut.printf("length = %d, ancestor = %d\n", sap.length(3, 11), sap.ancestor(3, 11));
        //  Expected 3 / 5
        StdOut.printf("length = %d, ancestor = %d\n", sap.length(9, 12), sap.ancestor(9, 12));
        // Expected 4 / 0
        StdOut.printf("length = %d, ancestor = %d\n", sap.length(7, 2), sap.ancestor(7, 2));
       // Expected -1 / -1
        StdOut.printf("length = %d, ancestor = %d\n", sap.length(1, 6), sap.ancestor(1, 6));

    }


}
