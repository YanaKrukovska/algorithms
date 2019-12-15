package ua.edu.ukma.ykrukovska.unit13.task3;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

import java.util.Set;
import java.util.TreeSet;

public class Degrees {
    private Digraph digraph;

    public Degrees(Digraph G) {
        this.digraph = G;
    }

    public int outdegree(int v) {
        int count = 0;
        for (int d : digraph.adj(v)) {
            count++;
        }
        return count;
    }

    public int indegree(int v) {
        int count = 0;
        for (int i = 0; i < digraph.V(); i++) {
            for (int d : digraph.adj(i)) {
                if (d == v) {
                    count++;
                }
            }
        }
        return count;
    }


    private Iterable<Integer> sources() {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < digraph.V(); i++) {
                if (indegree(i) == 0) {
                    set.add(i);
                }

        }
        return set;
    }


    private Iterable<Integer> sinks() {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < digraph.V(); i++) {
            for (int a : digraph.adj(i)) {
                if (outdegree(a) == 0) {
                    set.add(a);
                }
            }
        }
        return set;
    }

    private boolean isMap() {
        for (int i = 0; i < digraph.V(); i++) {
            for (int a : digraph.adj(i)) {
                if (outdegree(a) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Digraph g = new Digraph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(8, 7);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(11, 12);
        Degrees deg = new Degrees(g);
        StdOut.println(deg.indegree(6));  // 1
        StdOut.println(deg.outdegree(6)); // 0
        StdOut.println(deg.outdegree(4)); // 0

        StdOut.print("Sources: ");
        for (int v : deg.sources()) {
            StdOut.print(v + " "); // 4 8 9
        }

       StdOut.print("\nSinks: ");
        for (int v : deg.sinks()) {
            StdOut.print(v + " "); // 1 4 5 6 7 10 12
        }

        StdOut.println("\nIs map: " + deg.isMap()); // false
    }
}
