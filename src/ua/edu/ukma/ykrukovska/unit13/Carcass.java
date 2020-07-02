package ua.edu.ukma.ykrukovska.unit13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Carcass {
    private static int[][] values;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] inputLine = in.readLine().split(" ");
        int n = Integer.parseInt(inputLine[0]);
        int m = Integer.parseInt(inputLine[1]);

        values = new int[m][3];

        for (int i = 0; i < m; ++i) {
            String[] line = in.readLine().split(" ");
            values[i][0] = Integer.parseInt(line[0]);
            values[i][1] = Integer.parseInt(line[1]);
            values[i][2] = Integer.parseInt(line[2]);
        }

        Arrays.sort(values, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        int[] tree = new int[n + 1];

        int sum = 0;
        for (int i = 0; i < m; i++) {
            if (tree[values[i][0]] == 0 || tree[values[i][0]] != tree[values[i][1]]) {
                sum += values[i][2];

                if (tree[values[i][0]] == 0 && tree[values[i][1]] == 0) {
                    int min = values[i][0];
                    if (min > values[i][1]) {
                        min = values[i][1];
                    }
                    tree[values[i][0]] = min;
                    tree[values[i][1]] = min;
                } else if (tree[values[i][0]] == 0) {
                    tree[values[i][0]] = tree[values[i][1]];
                } else if (tree[values[i][1]] == 0) {
                    tree[values[i][1]] = tree[values[i][0]];
                } else {
                    if (tree[values[i][0]] > tree[values[i][1]]) {
                        int kk = tree[values[i][0]];
                        for (int j = 1; j < n + 1; ++j) {
                            if (tree[j] == kk) {
                                tree[j] = tree[values[i][1]];
                            }
                        }
                    } else {
                        int kk = tree[values[i][1]];
                        for (int j = 1; j < n + 1; ++j) {
                            if (tree[j] == kk) {
                                tree[j] = tree[values[i][0]];
                            }
                        }
                    }
                }
            }

        }
        System.out.println(sum);
    }
}