package ua.edu.ukma.ykrukovska.unit10.evolution;

import java.math.BigInteger;
import java.util.Scanner;

public class Evolution {

    private final static BigInteger NEGATIVE_ONE = new BigInteger("-1");
    private final static BigInteger TWO = new BigInteger("2");


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int amount = scanner.nextInt();
        BigInteger number1 = scanner.nextBigInteger();
        BigInteger number2 = scanner.nextBigInteger();


        while (!number1.equals(number2)) {
            if (number1.compareTo(number2) > 0 && number1.compareTo(BigInteger.ONE) > 0) {
                number1 = updateNumber(number1);
            } else if (number2.compareTo(BigInteger.ONE) > 0) {
                number2 = updateNumber(number2);
            } else break;

        }
        System.out.println(number1);

    }


    private static BigInteger updateNumber(BigInteger number) {

        if (number.mod(TWO).equals(1)) {
            number = number.add(NEGATIVE_ONE);
        }

        return  number.divide(TWO);
    }
}