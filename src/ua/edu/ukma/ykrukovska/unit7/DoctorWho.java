package ua.edu.ukma.ykrukovska.unit7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class DoctorWho {

    public static void main(String[] args) throws IOException {

        String input;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> lines = new ArrayList<>();

        while ((input = in.readLine()) != null) {
            lines.add(input);
        }

        for (String line : lines) {

            System.out.println(findPairs(line) ? "ok" : "fail");
            System.out.println("");

        }


    }


    public static boolean findPairs(String data) {

        String[] splitLine = data.split(" ");

        ArrayList<Integer> numbers = new ArrayList<>();


        for (String number : splitLine) {
            numbers.add(Integer.parseInt(number));
        }


        return process(numbers);
    }


    public static boolean process(ArrayList<Integer> guests) {

        guests.sort((o1, o2) -> (o1 < o2) ? 1 : ((o1.equals(o2)) ? 0 : -1));

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