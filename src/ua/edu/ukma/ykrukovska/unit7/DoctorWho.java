package ua.edu.ukma.ykrukovska.unit7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DoctorWho {

    public static void main(String[] args) throws IOException {

        String input;
        Scanner in = new Scanner(System.in);

        input = in.nextLine();


        String[] split = input.split(" ");
        if (split.length <= 10000 && validateInput(split)) {
            System.out.println(findPairs(input) ? "ok" : "fail");
            System.out.println("");

        }

    }

    private static boolean validateInput(String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (Integer.parseInt(split[i]) > 1000) {
                return false;
            }
        }
        return true;
    }


    public static boolean findPairs(String data) {

        String[] split = data.split(" ");

        ArrayList<Integer> list = new ArrayList<>();


        for (int j = 0; j < split.length; j++) {
            list.add(Integer.parseInt(split[j]));
        }


        boolean result = process(list);


        return result;
    }


    public static boolean process(ArrayList<Integer> guests) {


        if (guests.size() == 0) {
            return true;
        }

        if (guests.size() == 1) {
            return false;
        }
        if (guests.size() == 2 && guests.get(0) == 1 && guests.get(1) == 1) {
            return true;
        }

        if (guests.size() == 2 && (guests.get(0) != 1 || guests.get(1) != 1)) {
            return false;
        }
        if (guests.get(0) > guests.size() - 1) {
            return false;
        }
        Integer desire = guests.get(0);


        for (int i = 1; i <= desire; i++) {
            guests.add(i, guests.get(i) - 1);
            guests.remove(i + 1);
        }
        guests.remove(0);

        Iterator<Integer> iter = guests.iterator();
        while (iter.hasNext()) {
            Integer p = iter.next();
            if (p == 0) {
                iter.remove();
            }
        }

        return process(guests);


    }
}