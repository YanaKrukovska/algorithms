package ua.edu.ukma.ykrukovska.unit11.undirectedGraph;

import java.util.Scanner;

public class UndirectedGraph {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int dimension = in.nextInt();
        int matrix[][] = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix[i][j] = in.nextInt();
                if (i == j && matrix[i][j] == 1) {
                    System.out.print("NO");
                    return;
                }
            }
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    System.out.print("NO");
                    return;
                }
            }
        }
        System.out.print("YES");
    }

}
