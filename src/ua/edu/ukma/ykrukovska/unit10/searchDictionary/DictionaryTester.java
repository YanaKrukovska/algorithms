package ua.edu.ukma.ykrukovska.unit10.searchDictionary;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DictionaryTester {

    public static void main(String[] args) throws IOException {


        Scanner in = new Scanner(System.in);
        Scanner numbers = new Scanner(System.in);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("D:/Studying/Algorithms/test/words.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SearchDictionary dictionary = new SearchDictionary();
        fillDictionary(bufferedReader, dictionary);



        String searchedWord;

        String wordToDelete = "nezarah";
        System.out.println("Is word " + wordToDelete + " present: " + dictionary.hasWord(wordToDelete));
        dictionary.delWord("nezarah");
        System.out.println("Is word " + wordToDelete + " present: " + dictionary.hasWord(wordToDelete));

        int wannaContinue = 0;
        do {
            System.out.println("Enter word");
            searchedWord = in.nextLine();
            if (searchedWord.charAt(searchedWord.length() - 1) == '*') {
                System.out.println(dictionary.query(searchedWord));
            } else {
                System.out.println("Is word " + searchedWord + " present: " + dictionary.hasWord(searchedWord));
            }

            System.out.println("Want to continue? 0 - no, 1 - yes");
            wannaContinue = numbers.nextInt();
        } while (wannaContinue != 0);

    }

    private static void fillDictionary(BufferedReader bufferedReader, SearchDictionary dictionary) {
        StringTokenizer token;
        while (true) {
            String line = null;
            try {
                line = bufferedReader.readLine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (line == null) {
                break;
            }
            token = new StringTokenizer(line);
            while (token.hasMoreTokens()) {
                dictionary.addWord(token.nextToken());
            }

        }
    }
}
