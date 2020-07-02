package ua.edu.ukma.ykrukovska.unit12.reversedGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReversedGraph {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String numbers = reader.readLine();
        int n = Integer.parseInt(numbers);
        StringBuilder[] result = new StringBuilder[n + 1];

        for (int i = 1; i <= n; i++) {
            numbers = reader.readLine();
            if (numbers.length() == 0)
                continue;
            String lines[] = numbers.split(" ");
            for (String line : lines) {
                int value = Integer.parseInt(line);
                if (result[value] == null) {
                    result[value] = new StringBuilder(String.valueOf(i));
                } else {
                    result[value].append(" ").append(i);
                }
            }
        }

        System.out.println(n);
        for (int i = 1; i <= n; i++) {
            if (result[i] != null) {
                System.out.println(result[i]);
            } else {
                System.out.println();
            }
        }

    }
}
