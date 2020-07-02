package ua.edu.ukma.ykrukovska.unit6.convexHull;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Arrays;
import java.util.Stack;


public class ConvexHull {
    private Stack<Point2D> hull = new Stack<>();

    private ConvexHull(Point2D[] points) {
        checkIfNull(points);

        int n = points.length;
        Point2D[] allPoints = new Point2D[n];
        for (int i = 0; i < n; i++) {
            allPoints[i] = points[i];
        }
        mergeSort(allPoints, 0, allPoints.length - 1);
        Arrays.sort(allPoints, 1, n, allPoints[0].polarOrder());
        hull.push(allPoints[0]);


        int equalCount;
        for (equalCount = 1; equalCount < n; equalCount++) {
            if (!allPoints[0].equals(allPoints[equalCount])) {
                break;
            }
        }

        int collinearCount;
        for (collinearCount = equalCount + 1; collinearCount < n; collinearCount++) {
            if (Point2D.ccw(allPoints[0], allPoints[equalCount], allPoints[collinearCount]) != 0) {
                break;
            }
        }
        hull.push(allPoints[collinearCount - 1]);


        for (int i = collinearCount; i < n; i++) {
            Point2D top = hull.pop();
            while (Point2D.ccw(hull.peek(), top, allPoints[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(allPoints[i]);
        }

    }

    private void checkIfNull(Point2D[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        if (points.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    private Iterable<Point2D> hull() {
        Stack<Point2D> stack = new Stack<>();
        for (Point2D p : hull) {
            stack.push(p);
        }
        return stack;
    }

    private static void merge(Point2D points[], int l, int middle, int r) {
        int n1 = middle - l + 1;
        int n2 = r - middle;

        Point2D left[] = new Point2D[n1];
        Point2D right[] = new Point2D[n2];

        for (int i = 0; i < n1; ++i) {
            left[i] = points[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            right[j] = points[middle + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (left[i].compareTo(right[j]) < 0) {
                points[k] = left[i];
                i++;
            } else {
                points[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            points[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            points[k] = right[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(Point2D points[], int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(points, left, middle);
            mergeSort(points, middle + 1, right);
            merge(points, left, middle, right);
        }
    }

    public static void main(String[] args) {

        In data = new In("D:/Studying/Algorithms/test/input400.txt");

        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-1000, 40000);
        StdDraw.setYscale(-1000, 40000);
        StdDraw.setPenRadius(0.005);
        StdDraw.enableDoubleBuffering();

        int n = data.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = data.readInt();
            int y = data.readInt();
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        ConvexHull graham = new ConvexHull(points);

        StdDraw.setPenRadius(0.003);
        for (int i = 1; i < graham.hull.size(); i++) {
            StdDraw.setPenColor(Color.BLUE);
            graham.hull.get(i - 1).drawTo(graham.hull.get(i));
        }
        graham.hull.get(0).drawTo(graham.hull.get(graham.hull.size() - 1));


        StdDraw.setPenRadius(0.015);
        StdDraw.setPenColor(Color.RED);
        for (Point2D p : graham.hull()) {
            p.draw();
        }

        StdDraw.show();

    }

}
