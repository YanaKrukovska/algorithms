package ua.edu.ukma.ykrukovska.unit9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PhoneNumbers {

    public static void main(String[] args) throws IOException {

        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        int amountOfTests = Integer.parseInt(scanner.readLine());

        for (int i = 0; i < amountOfTests; i++) {
            int amountOfNumbers = Integer.parseInt(scanner.readLine());

            String[] numbers = new String[amountOfNumbers];
            for (int j = 0; j < amountOfNumbers; j++) {
                numbers[j] = scanner.readLine();
            }
            System.out.println(check(numbers) ? "YES" : "NO");
        }
    }

    private static boolean check(String[] numbers) {

        Arrays.sort(numbers);

        for (int i = 0; i < numbers.length - 1; i++) {
            int size = numbers[i].length();

            if (size < numbers[i + 1].length() || size == numbers[i + 1].length()) {
                if (numbers[i].equals(numbers[i + 1].substring(0, size))) {
                    return false;
                }
            }
        }
        return true;
    }
}
