package ua.edu.ukma.ykrukovska.unit7;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Queue {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int queueSize = in.nextInt();
        int amount = in.nextInt();

        PriorityQueue<Long> queue = new PriorityQueue<>();

        for (int i = 0; i < queueSize; i++) {

           long customer = in.nextLong();

            if (queue.size() != amount) {
                queue.add(customer);
            } else {
                queue.add(queue.poll() + customer);
            }

        }

        while (queue.size() > 1) {
            queue.poll();
        }
        System.out.println(queue.poll());
    }
}
