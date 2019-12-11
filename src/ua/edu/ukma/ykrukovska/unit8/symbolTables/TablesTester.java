package ua.edu.ukma.ykrukovska.unit8.symbolTables;

import java.util.Scanner;

public class TablesTester {

    private static int input;
    private static String stringInput;
    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scannerString = new Scanner(System.in);
    private static ST<Integer, String> st = new ST();
    private static BST<Integer, String> bst = new BST();
    private static int keyInput;

    public static void main(String[] args) {
        while (true) {
            System.out.println("ST - 1");
            System.out.println("BST - 2");
            input = scanner.nextInt();
            if (input == 0) break;
            switch (input) {
                case 1:
                    st();
                    break;
                case 2:
                    bst();
                    break;
            }
        }
    }

    private static void st() {
        while (true) {
            System.out.println("1. Put");
            System.out.println("2. Get");
            System.out.println("3. Delete");
            System.out.println("4. Contains");
            System.out.println("5. is Empty?");
            System.out.println("6. Size");
            System.out.println("7. Min");
            System.out.println("8. Max");
            System.out.println("9. Delete Min");
            System.out.println("10. Delete Max");
            System.out.println("11. Ceiling");
            System.out.println("12. Floor");
            System.out.println("13. Rank");
            System.out.println("14. Select");
            System.out.println("15. Size in range");
            System.out.println("16. Print All");
            System.out.println("17. Print in range");
            System.out.println("Press 0 to exit");
            input = scanner.nextInt();
            if (input == 0) break;
            switch (input) {
                case 1:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Value: ");
                    stringInput = scannerString.nextLine();
                    st.put(keyInput, stringInput);
                    break;
                case 2:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println(st.get(keyInput));
                    break;
                case 3:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    st.delete(keyInput);
                    System.out.println("Key = " + keyInput + "; Value = " + st.get(keyInput));
                    break;
                case 4:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println(st.contains(keyInput));
                    break;
                case 5:
                    System.out.println(st.isEmpty());
                    break;
                case 6:
                    System.out.println("Size = " + st.size());
                    break;
                case 7:
                    System.out.println("Min = " + st.min() + ", " + st.get(st.min()));
                    break;
                case 8:
                    System.out.println("Max = " + st.max() + ", " + st.get(st.max()));
                    break;
                case 9:
                    System.out.println("Previous min = " + st.min() + ", " + st.get(st.min()));
                    st.deleteMin();
                    System.out.println("New min = " + st.min() + ", " + st.get(st.min()));
                    break;
                case 10:
                    System.out.println("Previous max = " + st.max() + ", " + st.get(st.max()));
                    st.deleteMax();
                    System.out.println("New max = " + st.max() + ", " + st.get(st.max()));
                    break;
                case 11:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Ceiling = " + st.ceiling(keyInput));
                    break;
                case 12:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Floor = " + st.floor(keyInput));
                    break;
                case 13:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println(st.rank(keyInput));
                    break;
                case 14:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println(st.select(keyInput));
                    break;
                case 15:
                    System.out.println("Key 1: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Key 2: ");
                    int keyInput2 = scanner.nextInt();
                    System.out.println(st.size(keyInput, keyInput2));
                    break;
                case 16:
                    System.out.println(st.keys());
                    break;
                case 17:
                    System.out.println("Key 1: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Key 2: ");
                    keyInput2 = scanner.nextInt();
                    System.out.println(st.keys(keyInput, keyInput2));
                    break;
            }

        }
    }

    private static void bst() {
        while (true) {
            System.out.println("1. Put");
            System.out.println("2. Get");
            System.out.println("3. Delete");
            System.out.println("4. Size");
            System.out.println("5. Min");
            System.out.println("6. Max");
            System.out.println("7. Ceiling");
            System.out.println("8. Floor");
            System.out.println("9. Rank");
            System.out.println("10. Print All");
            System.out.println("Press 0 to exit");
            input = scanner.nextInt();
            if (input == 0) break;
            switch (input) {
                case 1:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Value: ");
                    stringInput = scannerString.nextLine();
                    bst.put(keyInput, stringInput);
                    break;
                case 2:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println(bst.get(keyInput));
                    break;
                case 3:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    bst.delete(keyInput);
                    break;
                case 4:
                    System.out.println("Size = " + bst.size());
                    break;
                case 5:
                    System.out.println("Min = " + bst.min());
                    break;
                case 6:
                    System.out.println("Max = " + bst.max());
                    break;
                case 7:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Ceiling = " + bst.ceiling(keyInput));
                    break;
                case 8:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println("Floor = " + bst.floor(keyInput));
                    break;
                case 9:
                    System.out.println("Key: ");
                    keyInput = scanner.nextInt();
                    System.out.println(bst.rank(keyInput));
                    break;
                case 10:
                    System.out.println(bst.iterator());
                    break;
            }
        }
    }

}
