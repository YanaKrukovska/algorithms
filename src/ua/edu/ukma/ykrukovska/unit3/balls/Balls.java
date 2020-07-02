package ua.edu.ukma.ykrukovska.unit3.balls;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Balls {

    private static Map<Integer, Integer> balls;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        if (n >= 1 && n <= 100000) {
            balls = new HashMap<>();
            Integer color;
            for (int i = 0; i < n; i++) {
                color = in.nextInt();
                if (color >= 1 && color <= 9) {
                    Integer colorCount = balls.get(color);
                    balls.put(color, colorCount == null ? 1 : ++colorCount);
                }
            }

            int biggestAmount = 0;
            for (Map.Entry<Integer, Integer> entry : balls.entrySet()) {

                if (entry.getValue() > biggestAmount) {
                    biggestAmount = entry.getValue();
                }

            }

            System.out.println(n - biggestAmount);
        }
    }


}
