package ua.edu.ukma.ykrukovska.unit6;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DnaSorting {

    private static int getInvCount(String input, int n) {
        int count = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if (input.charAt(i) > input.charAt(j)) {
                    count++;
                }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        System.out.println();

        Set<DnaLine> tree = new TreeSet<>((line1, line2) -> {
            if (line1.value != line2.value) {
                return Integer.compare(line1.value, line2.value);
            } else {
                return Integer.compare(line1.order, line2.order);
            }
        });

        for (int i = 0; i < m; i++) {
            int n = in.nextInt();
            int lineCount = in.nextInt();
            in.nextLine();

            for (int j = 0; j < lineCount; j++) {
                String input = in.nextLine();
                tree.add(new DnaLine(input, getInvCount(input, n), j));
            }

            for (DnaLine dnaLine : tree) {
                System.out.print(dnaLine.key + "\n");
            }
            System.out.println();
            tree.clear();
        }
    }

    private static class DnaLine {
        private String key;
        private int value;
        private int order;

        DnaLine(String input, int count, int order) {
            key = input;
            value = count;
            this.order = order;
        }
    }

}
