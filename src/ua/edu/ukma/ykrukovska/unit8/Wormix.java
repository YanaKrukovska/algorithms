package ua.edu.ukma.ykrukovska.unit8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Wormix {

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private static int knapSack(int restOfPoints, int allPoints[], int timeValues[], int amountOfRounds) {
        int result[][] = new int[amountOfRounds + 1][restOfPoints + 1];

        for (int i = 0; i <= amountOfRounds; i++) {
            for (int w = 0; w <= restOfPoints; w++) {
                if (i == 0 || w == 0) {
                    result[i][w] = 0;
                } else if (allPoints[i - 1] <= w) {
                    result[i][w] = max(timeValues[i - 1] + result[i - 1][w - allPoints[i - 1]], result[i - 1][w]);
                } else {
                    result[i][w] = result[i - 1][w];
                }
            }
        }

        return result[amountOfRounds][restOfPoints];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader stringScanner = new BufferedReader(new InputStreamReader(System.in));
        String line = stringScanner.readLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int k = Integer.parseInt(line.split(" ")[1]);

        int[] points = new int[n];
        int[] times = new int[n];
        int sumPoints = 0;
        int sumTime = 0;

        for (int i = 0; i < n; i++) {
            line = stringScanner.readLine();
            int point = Integer.parseInt(line.split(" ")[0]);
            int time = Integer.parseInt(line.split(" ")[1]);
            points[i] = point;
            times[i] = time;
            sumPoints += point;
            sumTime += time;
        }

        if (sumPoints >= k) {
            System.out.println(sumTime - knapSack(sumPoints - k, points, times, n));

        } else {
            System.out.println(-1);

        }


    }
}
