package ua.edu.ukma.ykrukovska.unit10.searchDictionary;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DictionaryTester {

    public static void main(String[] args)  {


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



        int userAction;
        String searchedWord;
        String newWord;
        String wordToDelete;
        do {
            showMenu();
            userAction = numbers.nextInt();

            switch (userAction) {
                case 1:
                    System.out.println("Add: Enter word");
                    newWord = in.nextLine();
                    if (!dictionary.hasWord(newWord)){
                        dictionary.addWord(newWord);
                    }
                    break;
                case 2:
                    System.out.println("Delete: Enter word");
                    wordToDelete = in.nextLine();
                      dictionary.delWord(wordToDelete);
                    break;
                case 3:
                    System.out.println("Search: Enter word");
                    searchedWord = in.nextLine();
                    if (searchedWord.charAt(searchedWord.length() - 1) == '*') {
                        System.out.println(dictionary.query(searchedWord));
                    } else {
                        System.out.println("Is word " + searchedWord + " present: " + dictionary.hasWord(searchedWord));
                    }
                    break;
                case 4:
                    System.out.println("Check: Enter word");
                    searchedWord = in.nextLine();
                    System.out.println("Is word " + searchedWord + " present: " + dictionary.hasWord(searchedWord));
                    break;
                case 5:
                    System.out.println(dictionary.countWords());
                    break;
            }

        } while (userAction != 0);


    }

    private static void showMenu() {
        System.out.println("1. Add word");
        System.out.println("2. Delete words");
        System.out.println("3. Search words");
        System.out.println("4. Check if word is present");
        System.out.println("5. Dictionary size");
        System.out.println("0. Exit");
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
