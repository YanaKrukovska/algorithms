package ua.edu.ukma.ykrukovska.unit2.gardener;

import java.util.Scanner;

public class Gardener {

    private static int calculateAmountOfWater(int amountOfStoreys) {

        int amountOfLeaves = 0;
        int litres = 1;

        for (int i = 1; i <= amountOfStoreys; i++) {
            amountOfLeaves += 2;
            litres += amountOfLeaves;
        }
        return litres;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n >= 0 && n <= 1000) {
            int result = calculateAmountOfWater(n);
            System.out.println(result);
        }
    }
}
