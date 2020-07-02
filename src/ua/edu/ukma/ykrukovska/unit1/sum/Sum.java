package ua.edu.ukma.ykrukovska.unit1.sum;

import java.util.Scanner;

public class Sum {

    private static int calculateLength(int n) {
        if (n == 1) {
            return 1;
        }
        return 1 + calculateLength(n % 2 == 0 ? n / 2 : 3 * n + 1);
    }

    private static int calculateMaxLength(int i, int j) {

        int biggerNumber;
        int smallerNumber;

        if (i > j) {
            biggerNumber = i;
            smallerNumber = j;
        } else {
            biggerNumber = j;
            smallerNumber = i;
        }

        int max = 0;
        for (int k = smallerNumber; k <= biggerNumber; k++) {
            int cycleLength = calculateLength(k);
            if (cycleLength > max) {
                max = cycleLength;
            }
        }

        return max;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int i = in.nextInt();
            int j = in.nextInt();
            if (i > 0 && i < 1000000 && j > 0 && j < 1000000) {
                long result = calculateMaxLength(i, j);

                System.out.println(i + " " + j + " " + result);
            }
        }
    }
}
