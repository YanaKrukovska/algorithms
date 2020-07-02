package ua.edu.ukma.ykrukovska.unit4.collinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {

        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicates(sortedPoints);

        int length = points.length;
        List<LineSegment> list = new LinkedList<>();

        for (int a = 0; a < length - 3; a++) {
            Point pointA = sortedPoints[a];
            for (int b = a + 1; b < length - 2; b++) {
                Point pointB = sortedPoints[b];
                double slopeAB = pointA.slopeTo(pointB);
                for (int c = b + 1; c < length - 1; c++) {
                    Point pointC = sortedPoints[c];
                    double slopeAC = pointA.slopeTo(pointC);
                    if (slopeAB == slopeAC) {
                        for (int d = c + 1; d < length; d++) {
                            Point pointD = sortedPoints[d];
                            double slopeAD = pointA.slopeTo(pointD);
                            if (slopeAB == slopeAD) {
                                list.add(new LineSegment(pointA, pointD));
                            }
                        }
                    }
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

        In data = new In("D:/Studying/Algorithms/test/rs1423.txt");

        int n = data.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = data.readInt();
            int y = data.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.setCanvasSize(650, 650);
        StdDraw.setXscale(-1000, 40000);
        StdDraw.setYscale(-1000, 40000);
        StdDraw.enableDoubleBuffering();
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}



