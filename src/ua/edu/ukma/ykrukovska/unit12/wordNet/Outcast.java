package ua.edu.ukma.ykrukovska.unit12.wordNet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;
    private static final String FILE_PATH = "D:/Studying/Algorithms/Practice12/";

    private Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    private String outcast(String[] nouns) {
        int max = -1;
        String finalNoun = "";
        for (String noun : nouns) {
            int current = 0;
            for (String noun1 : nouns) {
                current += wordnet.distance(noun, noun1);
            }
            if (current > max) {
                max = current;
                finalNoun = noun;
            }
        }
        return finalNoun;
    }

    public static void main(String[] args) {

        WordNet wordnet = new WordNet(FILE_PATH + "synsets.txt", FILE_PATH + "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);

        StdOut.println(outcast.outcast(new In(FILE_PATH + "outcast5.txt").readAllStrings()));
        StdOut.println(outcast.outcast(new In(FILE_PATH + "outcast8.txt").readAllStrings()));
        StdOut.println(outcast.outcast(new In(FILE_PATH + "outcast11.txt").readAllStrings()));


    }
}