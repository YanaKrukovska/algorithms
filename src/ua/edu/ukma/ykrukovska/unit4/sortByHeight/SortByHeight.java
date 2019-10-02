package ua.edu.ukma.ykrukovska.unit4.sortByHeight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortByHeight {


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Insert n: ");
        int n = Integer.parseInt(in.readLine());

        System.out.println("Insert sizes: ");
        int[] sizes = new int[n];
        String[] str = in.readLine().split(" ");


        for (int i = 0; i < n; i++) {
            sizes[i] = Integer.parseInt(str[i]);
        }

        System.out.println("Insert min and max sizes: ");
        str = in.readLine().split(" ");
        int minHeight = Integer.parseInt(str[0]);
        int maxHeight = Integer.parseInt(str[1]);

        int amount = 0;

        for (int size : sizes) {
            if (size >= minHeight && size <= maxHeight) {
                amount++;
            }
        }


        System.out.println(amount);


    }

}
