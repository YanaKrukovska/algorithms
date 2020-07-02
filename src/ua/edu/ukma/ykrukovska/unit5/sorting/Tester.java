package ua.edu.ukma.ykrukovska.unit5.sorting;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.Map;

import static ua.edu.ukma.ykrukovska.unit5.sorting.Sorter.SortingType.*;


public class Tester {

    private static final int DATA_SIZE = 10_000;
    private static final int MIN_NUMBER = -10000;
    private static final int MAX_NUMBER = 10000;
    private static Sorter sorter = new Sorter();

    public static void main(String[] args) {
        //  runExperiments();
        checkStability();

    }

    private static void runExperiments() {
        System.out.println("Random data, size = " + DATA_SIZE);
        testData(createRandomTestData(DATA_SIZE));

        System.out.println("Equal data, size = " + DATA_SIZE);
        testData(createEqualTestData(DATA_SIZE));

        System.out.println("Sorted data, size = " + DATA_SIZE);
        testData(createSortedTestData(DATA_SIZE));

        System.out.println("Sorted reversed data, size = " + DATA_SIZE);
        testData(createReversedSortedTestData(DATA_SIZE));
    }

    private static void checkStability() {
        Wrapper[] data =
                {
                        new Wrapper("Item 1", -90),
                        new Wrapper("Item 2", 6),
                        new Wrapper("Item 3", -90),
                        new Wrapper("Item 4", -90),
                        new Wrapper("Item 5", 2),
                        new Wrapper("Item 6", 2),
                        new Wrapper("Item 7", 1),
                        new Wrapper("Item 8", 0),};


        for (Wrapper aResultData : data) {
            System.out.println(aResultData.toString());
        }

        System.out.println("\n" + "Bubble sort: ");

        for (Wrapper aResultData : sorter.sort(data.clone(), BUBBLE)) {
            System.out.println(aResultData.toString());
        }

        System.out.println("Merge sort: ");

        for (Wrapper aResultData : sorter.sort(data.clone(), MERGE)) {
            System.out.println(aResultData.toString());
        }

        System.out.println("Quick sort: ");

        for (Wrapper aResultData : sorter.sort(data.clone(), QUICK)) {
            System.out.println(aResultData.toString());
        }

        System.out.println("Insertion sort: ");

        for (Wrapper aResultData : sorter.sort(data.clone(), INSERTION)) {
            System.out.println(aResultData.toString());
        }

        System.out.println("Selection sort: ");

        for (Wrapper aResultData : sorter.sort(data.clone(), SELECTION)) {
            System.out.println(aResultData.toString());
        }
        System.out.println("Shell sort: ");

        for (Wrapper aResultData : sorter.sort(data.clone(), SHELL)) {
            System.out.println(aResultData.toString());
        }

    }


    private static void testData(Wrapper[] testData) {

        HashMap<String, Double> testResults = new HashMap<>();

        Stopwatch stopwatch = new Stopwatch();
        sorter.sort(testData.clone(), BUBBLE);
        testResults.put(BUBBLE.name(), stopwatch.elapsedTime());

        stopwatch = new Stopwatch();
        sorter.sort(testData.clone(), INSERTION);
        testResults.put(INSERTION.name(), stopwatch.elapsedTime());

        stopwatch = new Stopwatch();
        sorter.sort(testData.clone(), SELECTION);
        testResults.put(SELECTION.name(), stopwatch.elapsedTime());

        stopwatch = new Stopwatch();
        sorter.sort(testData.clone(), SHELL);
        testResults.put(SHELL.name(), stopwatch.elapsedTime());

        stopwatch = new Stopwatch();
        sorter.sort(testData.clone(), MERGE);
        testResults.put(MERGE.name(), stopwatch.elapsedTime());

        stopwatch = new Stopwatch();
        sorter.sort(testData.clone(), QUICK);
        testResults.put(QUICK.name(), stopwatch.elapsedTime());

        for (Map.Entry<String, Double> e : testResults.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
        System.out.println(" ");
    }

    private static Wrapper[] createStabilityTestData(int size) {

        Wrapper[] data = new Wrapper[size];

        int number = 1;
        for (int i = 0; i < size; i++) {

            data[i] = new Wrapper("Item " + i, number);
        }
        return data;
    }

    private static Wrapper[] createRandomTestData(int size) {

        Wrapper[] data = new Wrapper[size];

        for (int i = 0; i < size; i++) {
            data[i] = new Wrapper("Item " + i, StdRandom.uniform(MIN_NUMBER, MAX_NUMBER));
        }
        return data;
    }

    private static Wrapper[] createEqualTestData(int size) {

        Wrapper[] data = new Wrapper[size];

        for (int i = 0; i < size; i++) {
            data[i] = new Wrapper("Item " + i, 1488);
        }
        return data;
    }

    private static Wrapper[] createSortedTestData(int size) {

        Wrapper[] data = new Wrapper[size];

        for (int i = 0; i < size; i++) {
            data[i] = new Wrapper("Item " + i, i);
        }
        return data;
    }

    private static Wrapper[] createReversedSortedTestData(int size) {

        Wrapper[] data = new Wrapper[size];

        int number = 0;
        for (int i = size - 1; i >= 0; i--) {
            data[i] = new Wrapper("Item " + i, number++);
        }
        return data;
    }


}
