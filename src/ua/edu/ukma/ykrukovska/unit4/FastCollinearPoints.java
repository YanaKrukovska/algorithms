package ua.edu.ukma.ykrukovska.unit4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] segments;


    public FastCollinearPoints(Point[] points) {
        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicates(sortedPoints);

        List<LineSegment> list = new LinkedList<>();
        int amountOfPoints = points.length;

        for (int i = 0; i < amountOfPoints; i++) {
            Point origin = sortedPoints[i];
            int count = 1;

            Point[] pointsSortedBySlope = sortedPoints.clone();
            Arrays.sort(pointsSortedBySlope, origin.slopeOrder());

            while (count < amountOfPoints) {
                LinkedList<Point> possiblePoints = new LinkedList<>();
                double slopeOrigin = origin.slopeTo(pointsSortedBySlope[count]);

                do {
                    possiblePoints.add(pointsSortedBySlope[count++]);
                } while (count < amountOfPoints && origin.slopeTo(pointsSortedBySlope[count]) == slopeOrigin);

                if (possiblePoints.size() >= 3 && origin.compareTo(possiblePoints.peek()) < 0) {
                    Point last = possiblePoints.peekLast();
                    list.add(new LineSegment(origin, last));
                }

            }
        }

        segments = list.toArray(new LineSegment[0]);

    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }


    private void checkNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
