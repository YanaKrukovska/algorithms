package ua.edu.ukma.ykrukovska.unit4.lostCard;


import java.util.Scanner;

public class LostCard {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);


        int n = in.nextInt();
        int sumGeneral = 0;

        for (int i = 1; i <= n; i++) {
            sumGeneral = sumGeneral + i;
        }

        int sumCurrent = 0;
        for (int i = 1; i < n; i++) {
            int number = in.nextInt();
            sumCurrent = sumCurrent + number;
        }

        System.out.println(sumGeneral - sumCurrent);
    }

}
