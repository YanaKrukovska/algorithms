package ua.edu.ukma.ykrukovska.unit5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RobotsMergeSort {

    private static class Robot implements Comparable<Robot> {
        int mainNumber, extraNumber, position;

        public Robot(int main, int extra, int pos) {
            mainNumber = main;
            extraNumber = extra;
            position = pos;
        }

        @Override
        public int compareTo(Robot that) {
            int mainDiff = this.mainNumber - that.mainNumber;
            return mainDiff == 0 ? this.position - that.position : mainDiff;

        }
    }

    private static void merge(Robot robots[], int l, int middle, int r) {
        int n1 = middle - l + 1;
        int n2 = r - middle;

        Robot left[] = new Robot[n1];
        Robot right[] = new Robot[n2];

        for (int i = 0; i < n1; ++i) {
            left[i] = robots[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            right[j] = robots[middle + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (left[i].compareTo(right[j]) < 0) {
                robots[k] = left[i];
                i++;
            } else {
                robots[k] = right[j];
           //     j++;
            }
            k++;
        }

        while (i < n1) {
            robots[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            robots[k] = right[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(Robot robots[], int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(robots, left, middle);
           // mergeSort(robots, middle + 1, right);
          //  merge(robots, left, middle, right);
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int amount = 0;
        boolean canContinue;
        do {
            try {
                amount = Integer.parseInt(in.readLine());
                canContinue = true;
            } catch (Exception e) {
                canContinue = false;
            }
        } while (!canContinue);


        Robot[] robots = new Robot[amount];

        for (int i = 0; i < robots.length; i++) {
            String input = in.readLine();
            String[] split = input.split(" ");
            robots[i] = new Robot(Integer.valueOf(split[0]), Integer.valueOf(split[1]), i);
        }

        mergeSort(robots, 0, robots.length - 1);

        StringBuilder sb = new StringBuilder();

        for (Robot robot : robots) {
            sb.append(robot.mainNumber).append(" ").append(robot.extraNumber).append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }


}
