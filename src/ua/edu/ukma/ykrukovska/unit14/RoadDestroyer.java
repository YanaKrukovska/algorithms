package ua.edu.ukma.ykrukovska.unit14;

import java.io.IOException;
import java.util.Scanner;

public class RoadDestroyer {

        public static void main(String[] args) throws IOException {
            Scanner in = new Scanner(System.in);

            while (in.hasNextLine()) {

                int n = Integer.parseInt(in.nextLine());
                int cities[][] = new int[n][n];
                for (int i = 0; i < n; i++) {
                    String input = in.nextLine();
                    String prices[] = input.split("");
                    for (int j = 0; j < n; j++) {
                        cities[i][j] = Integer.parseInt(prices[j]);
                    }
                }

                int minPrice = 999999999;
                for (int i = 1; i < n; i++) {
                    minPrice = Math.min(minPrice, findMinPrice(i, cities));
                }

                System.out.println(minPrice);
            }

        }

        private static int findMinPrice(int currentCity, int[][] cities) {
            int size = cities.length;
            int citiesCopy[][] = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    citiesCopy[i][j] = cities[i][j];
                }
            }
            boolean visited[] = new boolean[size];
            int x, price = 0;
            do {
                for (int i = 0; i < visited.length; ++i) {
                    visited[i] = false;
                }
                x = findCap(0, currentCity, 999999999, visited, citiesCopy);
                price += x;
            } while (x != 0 && price != 0);
            return price;
        }

        private static int findCap(int x, int t, int roadCurrent, boolean visited[], int result[][]) {
            if (x == t) {
                return roadCurrent;
            }
            if (visited[x]) {
                return 0;
            }
            visited[x] = true;

            for (int road, y = 0; y < result.length; ++y) {
                if (result[x][y] > 0 && (road = findCap(y, t, Math.min(roadCurrent, result[x][y]), visited, result)) > 0) {
                    result[x][y] -= road;
                    result[y][x] += road;
                    return road;
                }
            }
            return 0;
        }

    }