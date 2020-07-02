package ua.edu.ukma.ykrukovska.unit3.stackMinimum;

import java.util.Scanner;
import java.util.Stack;

public class StackMinimum {

    private static Stack<Long> stack = new Stack<>();
    private static long result = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        long n = in.nextLong();
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long x0 = in.nextLong();

        if (validate(n, a, b, c, x0)) {
            for (int i = 1; i <= n; i++) {

                long x = ((a * x0 * x0 + b * x0 + c) / 100) % 1_000_000;


                if (x % 5 < 2) {
                    if (!stack.empty()) {
                        stack.pop();
                    }
                } else {
                    if (stack.empty()) {
                        stack.push(x);
                    } else {
                        stack.push(Math.min(stack.peek(), x));
                    }
                }
                if (!stack.empty()) {
                    result += stack.peek();
                }

                x0 = x;
            }
            System.out.println(result);
        }

    }

    private static boolean validate(long n, long a, long b, long c, long x0) {
        return n >= 1 && n <= 1_000_000 && a >= 0 && b >= 0 && c >= 0 && x0 >= 0 && a <= 10_000 && b <= 10_000 && c <= 10_000 && x0 <= 10_000;
    }
}
