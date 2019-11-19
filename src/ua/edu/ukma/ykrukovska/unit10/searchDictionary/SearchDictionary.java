package ua.edu.ukma.ykrukovska.unit10.searchDictionary;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class SearchDictionary {

    private TreeSet<String> dictionary;

    public SearchDictionary() {
        dictionary = new TreeSet<>();
    }

    public void addWord(String word) {
        dictionary.add(word);
    }

    public String delWord(String word) {
        return dictionary.remove(word) ? word : null;
    }

    public boolean hasWord(String word) {
        return dictionary.contains(word);
    }

    public Iterable<String> query(String query) {
        String requestQuery = query.replaceAll("\\*", "\\\\w*");
        Pattern pattern = Pattern.compile(requestQuery);
        ArrayList<String> list = new ArrayList<String>();
        for (String str : this.dictionary) {
            if (pattern.matcher(str).matches())
                list.add(str);
        }

        return list;
    }

    public int countWords() {
        return dictionary.size();
    }

}
