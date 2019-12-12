package ua.edu.ukma.ykrukovska.unit13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UnionDay {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        int[][] cities = new int[n][4];
        double[] minDistance = new double[n];
        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n; ++i) {
            String[] st = in.readLine().split(" ");
            cities[i][0] = Integer.parseInt(st[0]);
            cities[i][1] = Integer.parseInt(st[1]);
            cities[i][2] = 10001;
            cities[i][3] = 10001;
            minDistance[i] = 999999999;
        }

        double result = 0;

        for (int i = 0; i < n; ++i) {

            int[] minDistanceCity = null;
            int cityNumber = 0;
            for (int j = 0; j < n; ++j) {
                if (!isVisited[j])
                    if (minDistanceCity == null || minDistance[j] < minDistance[cityNumber]) {
                        minDistanceCity = cities[j];
                        cityNumber = j;
                    }
            }
            isVisited[cityNumber] = true;

            if (minDistanceCity[2] != 10001) {
                result += Math.sqrt((minDistanceCity[0] - minDistanceCity[2]) * (minDistanceCity[0] - minDistanceCity[2])
                        + (minDistanceCity[1] - minDistanceCity[3]) * (minDistanceCity[1] - minDistanceCity[3]));
            }

            for (int j = 0; j < n; ++j) {
                double distance = Math.sqrt((minDistanceCity[0] - cities[j][0]) * (minDistanceCity[0] - cities[j][0])
                        + (minDistanceCity[1] - cities[j][1]) * (minDistanceCity[1] - cities[j][1]));
                if (!isVisited[j] && distance < minDistance[j]) {
                    minDistance[j] = distance;
                    cities[j][2] = minDistanceCity[0];
                    cities[j][3] = minDistanceCity[1];
                }
            }

        }
        System.out.println(result);
    }

}