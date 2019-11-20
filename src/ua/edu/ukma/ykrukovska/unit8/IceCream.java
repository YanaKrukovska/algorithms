package ua.edu.ukma.ykrukovska.unit8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IceCream {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String numbersInput[] = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(numbersInput[0]);
        int k = Integer.parseInt(numbersInput[1]);

        String coordinatesInput[] = bufferedReader.readLine().split(" ");
        int[] coordinates = new int[n];
        for (int i = 0; i < n; ++i) {
            coordinates[i] = Integer.parseInt(coordinatesInput[i]);
        }

        int left = 0;
        int right = 1000000000;
        while (left <= right) {
            int middle = (left + right) / 2;

            if (ableToPlace(middle, n, k, coordinates)) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        System.out.println(left - 1);

    }

    private static boolean ableToPlace(int middle, int spots, int workers, int[] coordinates) {
        int stallsWithWorkers = 1;
        int length = 0;
        for (int i = 1; i < spots; ++i) {
            length += coordinates[i] - coordinates[i - 1];
            if (length >= middle) {
                length = 0;
                ++stallsWithWorkers;
            }
        }
        return stallsWithWorkers >= workers;
    }

}