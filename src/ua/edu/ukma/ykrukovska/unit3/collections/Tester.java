package ua.edu.ukma.ykrukovska.unit3.collections;

import java.util.Scanner;

public class Tester {
    private static Scanner in = new Scanner(System.in);
    private static Bag<Integer> bag = new Bag<>();
    private static ArrayStack<Integer> arrayStack = new ArrayStack<>();
    private static ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
    private static LinkedStack<Integer> linkedStack = new LinkedStack<>();
    private static LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

    public static void main(String[] args) {
        int userAction;

        do {
            showMenu();
            userAction = getUserInput(5);

            switch (userAction) {
                case 1:
                    openBagMenu();
                    break;
                case 2:
                    openArrayStackMenu();
                    break;
                case 3:
                    openArrayQueueMenu();
                    break;
                case 4:
                    openLinkedStackMenu();
                    break;
                case 5:
                    openLinkedQueueMenu();
                    break;
            }

        } while (userAction != 0);

    }

    private static void openLinkedQueueMenu() {
        int userAction;
        do {
            System.out.println("Робота з linked queue");
            System.out.println("1. Додати елемент");
            System.out.println("2. Перевірити на пустоту");
            System.out.println("3. Дізнатися розмір");
            System.out.println("4. Видалити і показати перший");
            System.out.println("5. Повернути перший елемент");
            System.out.println("6. Показати усі елементи");
            System.out.println("0. Вихід.");
            userAction = getUserInput(6);

            switch (userAction) {
                case 1:
                    System.out.println("Введіть число: ");
                    int n = getUserInt();
                    linkedQueue.enqueue(n);
                    break;
                case 2:
                    System.out.println("Чи linked queue пуста: " + linkedQueue.isEmpty());
                    break;
                case 3:
                    System.out.println("Розмір: " + linkedQueue.size());
                    break;
                case 4:
                    System.out.println("Розмір: " + linkedQueue.size());
                    System.out.println("Видалено перший: " + linkedQueue.dequeue());
                    System.out.println("Розмір: " + linkedQueue.size());
                    break;
                case 5:
                    System.out.println("Перший елемент в черзі: " + linkedQueue.peek());
                    break;
                case 6:
                    System.out.println("Всі елементи: ");
                    for (int i : linkedQueue) {
                        System.out.println(i);
                    }
                    break;
            }
        } while (userAction != 0);
    }

    private static void openLinkedStackMenu() {
        int userAction;
        do {
            System.out.println("Робота з linked stack");
            System.out.println("1. Додати елемент");
            System.out.println("2. Перевірити на пустоту");
            System.out.println("3. Дізнатися розмір");
            System.out.println("4. Видалити і показати останній");
            System.out.println("5. Повернути останній елемент");
            System.out.println("6. Показати усі елементи");
            System.out.println("0. Вихід.");
            userAction = getUserInput(6);

            switch (userAction) {
                case 1:
                    System.out.println("Введіть число: ");
                    int n = getUserInt();
                    linkedStack.push(n);
                    break;
                case 2:
                    System.out.println("Чи linked stack пустий: " + linkedStack.isEmpty());
                    break;
                case 3:
                    System.out.println("Розмір: " + linkedStack.size());
                    break;
                case 4:
                    System.out.println("Розмір: " + linkedStack.size());
                    System.out.println("Видалено останній: " + linkedStack.pop());
                    System.out.println("Розмір: " + linkedStack.size());
                    break;
                case 5:
                    System.out.println("Останній: " + linkedStack.peek());
                    break;
                case 6:
                    System.out.println("Всі елементи: ");
                    for (int i : linkedStack) {
                        System.out.println(i);
                    }
                    break;
            }
        } while (userAction != 0);

    }

    private static void openArrayQueueMenu() {
        int userAction;
        do {
            System.out.println("Робота з array queue");
            System.out.println("1. Додати елемент");
            System.out.println("2. Перевірити на пустоту");
            System.out.println("3. Дізнатися розмір");
            System.out.println("4. Видалити і показати перший");
            System.out.println("5. Повернути перший елемент");
            System.out.println("6. Показати усі елементи");
            System.out.println("0. Вихід.");
            userAction = getUserInput(6);

            switch (userAction) {
                case 1:
                    System.out.println("Введіть число: ");
                    int n = getUserInt();
                    arrayQueue.enqueue(n);
                    break;
                case 2:
                    System.out.println("Чи array stack пустий: " + arrayQueue.isEmpty());
                    break;
                case 3:
                    System.out.println("Розмір: " + arrayQueue.size());
                    break;
                case 4:
                    System.out.println("Розмір: " + arrayQueue.size());
                    System.out.println("Видалено останній: " + arrayQueue.dequeue());
                    System.out.println("Розмір: " + arrayQueue.size());
                    break;
                case 5:
                    System.out.println("Останній: " + arrayQueue.peek());
                    break;
                case 6:
                    System.out.println("Всі елементи: ");
                    for (int i : arrayQueue) {
                        System.out.println(i);
                    }
                    break;
            }
        } while (userAction != 0);
    }

    private static void openArrayStackMenu() {
        int userAction;
        do {
            System.out.println("Робота з array stack");
            System.out.println("1. Додати елемент");
            System.out.println("2. Перевірити на пустоту");
            System.out.println("3. Дізнатися розмір");
            System.out.println("4. Видалити і показати останній");
            System.out.println("5. Повернути останній елемент");
            System.out.println("6. Показати усі елементи");
            System.out.println("0. Вихід.");
            userAction = getUserInput(6);

            switch (userAction) {
                case 1:
                    System.out.println("Введіть число: ");
                    int n = getUserInt();
                    arrayStack.push(n);
                    break;
                case 2:
                    System.out.println("Чи array stack пустий: " + arrayStack.isEmpty());
                    break;
                case 3:
                    System.out.println("Розмір: " + arrayStack.size());
                    break;
                case 4:
                    System.out.println("Розмір: " + arrayStack.size());
                    System.out.println("Видалено останній: " + arrayStack.pop());
                    System.out.println("Розмір: " + arrayStack.size());
                    break;
                case 5:
                    System.out.println("Останній: " + arrayStack.peek());
                    break;
                case 6:
                    System.out.println("Всі елементи: ");
                    for (int i : arrayStack) {
                        System.out.println(i);
                    }
                    break;
            }
        } while (userAction != 0);
    }

    private static void openBagMenu() {
        int userAction;
        do {
            System.out.println("Робота з bag");
            System.out.println("1. Додати елемент");
            System.out.println("2. Перевірити на пустоту");
            System.out.println("3. Дізнатися розмір");
            System.out.println("4. Показати всі елементи");
            System.out.println("0. Вихід.");
            userAction = getUserInput(4);

            switch (userAction) {
                case 1:
                    System.out.println("Введіть число: ");
                    int n = getUserInt();
                    bag.add(n);
                    break;
                case 2:
                    System.out.println("Чи мішок пустий: " + bag.isEmpty());
                    break;
                case 3:
                    System.out.println("Розмір: " + bag.size());
                    break;
                case 4:
                    System.out.println("Всі елементи: ");
                    for (int i : bag) {
                        System.out.println(i);
                    }
                    break;
            }
        } while (userAction != 0);
    }


    private static void showMenu() {
        System.out.println("1. Робота з Bag");
        System.out.println("2. Робота з Array Stack");
        System.out.println("3. Робота з Array Queue");
        System.out.println("4. Робота з Linked Stack");
        System.out.println("5. Робота з Linked Queue");
        System.out.println("0. Вихід.");
    }



    private static int getUserInput(int max) {
        System.out.println("Введіть: " + "номер операції");
        String userInput;
        int userInputNumber;
        do {
            userInput = in.nextLine();
        } while (!isStringInteger(userInput));

        userInputNumber = Integer.parseInt(userInput);
        userInputNumber = checkValues(userInputNumber, max);

        return userInputNumber;
    }


    private static boolean isStringInteger(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static int checkValues(int userInput, int max) {
        if (userInput < 0 || userInput > max) {
            System.out.println("Введено неправильне значення " + "номер операції");
            while (userInput < 0 || userInput > max) {
                System.out.println("Введіть " + 0 + " <= " + "номер операції" + " <= " + max);
                userInput = in.nextInt();
            }
        }
        return userInput;
    }

    private static int getUserInt() {
        String userInput;
        int userInputNumber;
        do {
            userInput = in.nextLine();
        } while (!isStringInteger(userInput));

        userInputNumber = Integer.parseInt(userInput);

        return userInputNumber;
    }

}
