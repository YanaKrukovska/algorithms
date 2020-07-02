package ua.edu.ukma.ykrukovska.unit2.bunnies;

import java.util.Scanner;

public class Bunnies {

    private static int breedBunnies(int months, int killed) {

        int amountOfBunnies = 1;

        for (int i = 1; i <= months; i++) {
            amountOfBunnies = amountOfBunnies * 2;
            if (amountOfBunnies > killed && i != months) {
                amountOfBunnies -= killed;
            }
        }
        return amountOfBunnies;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        int k = in.nextInt();
        if (i >= 0 && i <= 100 && k >= 0 && k <= 10000) {
            int result = breedBunnies(i, k);
            System.out.println(result);
        }
    }
}