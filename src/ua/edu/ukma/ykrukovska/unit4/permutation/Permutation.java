package ua.edu.ukma.ykrukovska.unit4.permutation;


import java.util.Scanner;

public class Permutation {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int missingNumber = 0;

        int[] numbers = new int[n + 1];


        for (int i = 0; i < n; i++) {
            int number = in.nextInt();
            if (number <= n) {
                numbers[number] = 1;
            }
        }


        for (int i = 1; i <= n; i++) {

            if (numbers[i] == 0){
                missingNumber = i;
                break;
            }
        }

        if (missingNumber <= n) {
            System.out.println(missingNumber);
        } else {
            System.out.println(0);

        }


    }

}
