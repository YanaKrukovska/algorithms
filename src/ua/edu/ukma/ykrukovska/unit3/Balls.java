package ua.edu.ukma.ykrukovska.unit3;

import java.io.Serializable;
import java.util.*;

import static java.util.stream.Collectors.toMap;

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

            Map<Integer, Integer> sorted = balls
                    .entrySet()
                    .stream()
                    .sorted(comparingByValueDesc())
                    .collect(
                            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                    LinkedHashMap::new));

            Iterator<Map.Entry<Integer, Integer>> iterator = sorted.entrySet().iterator();
            int biggestAmount = iterator.next().getValue();
            System.out.println(n-biggestAmount);

        }
    }

    public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValueDesc() {
        return (Comparator<Map.Entry<K, V>> & Serializable)
                (c1, c2) -> c2.getValue().compareTo(c1.getValue());
    }
}
