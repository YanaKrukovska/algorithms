package ua.edu.ukma.ykrukovska.unit12.wordNet;
import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.HashMap;

public class WordNet {

    private static final String FILE_PATH = "D:/Studying/Algorithms/Practice12/";
    private final SAP sap;
    private final HashMap<Integer, String> idToSynset;
    private final HashMap<String, ArrayList<Integer>> synsetToId;


    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        idToSynset = new HashMap<>();
        synsetToId = new HashMap<>();

        In synsetIn = new In(synsets);
        In hypernymsIn = new In(hypernyms);

        while (!synsetIn.isEmpty()) {
            String[] tokens = synsetIn.readLine().split(",");
            int id = Integer.parseInt(tokens[0]);
            String synsetString = tokens[1];
            String[] synsetSet = synsetString.split(" ");
            idToSynset.put(id, synsetString);
            for (String synset : synsetSet) {
                if (!synsetToId.containsKey(synset))
                    synsetToId.put(synset, new ArrayList<>());
                synsetToId.get(synset).add(id);
            }
        }


        Digraph g = new Digraph(idToSynset.size());
        while (!hypernymsIn.isEmpty()) {
            String[] tokens = hypernymsIn.readLine().split(",");
            int synset = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                int hypernym = Integer.parseInt(tokens[i]);
                g.addEdge(synset, hypernym);
            }
        }

        if (new DirectedCycle(g).hasCycle())
            throw new IllegalArgumentException();

        int roots = 0;
        for (int i = 0; i < g.V(); i++)
            if (!g.adj(i).iterator().hasNext())
                roots++;

        if (roots > 1)
            throw new IllegalArgumentException();

        sap = new SAP(g);
    }


    public Iterable<String> nouns() {
        return synsetToId.keySet();
    }

    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return synsetToId.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException();
        }
        return sap.length(synsetToId.get(nounA), synsetToId.get(nounB));
    }


    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException();
        }
        return idToSynset.get(sap.ancestor(synsetToId.get(nounA), synsetToId.get(nounB)));

    }

    public static void main(String[] args) {
        In in = new In(FILE_PATH + "digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int v = StdIn.readInt();
        System.out.println(v);
        while (!StdIn.isEmpty()) {
            // int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
