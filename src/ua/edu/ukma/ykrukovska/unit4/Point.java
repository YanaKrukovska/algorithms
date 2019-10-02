package ua.edu.ukma.ykrukovska.unit4;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {

        if (that.y == this.y && that.x == this.x) {
            return Double.NEGATIVE_INFINITY;
        } else if (that.y == this.y) {
            return +0.0;
        } else if (that.x == this.x) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (double) (that.y - this.y) / (that.x - this.x);
        }
    }

    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else if (this.y == that.y && this.x == that.x) {
            return 0;
        } else {
            return 1;
        }
    }


    public Comparator<Point> slopeOrder() {
        return new SlopeComparator(this);
    }

    private class SlopeComparator implements Comparator<Point> {

        private final Point point;

        SlopeComparator(Point point) {
            this.point = point;
        }

        @Override
        public int compare(Point p1, Point p2) {
            double slope1 = p1.slopeTo(point);
            double slope2 = p2.slopeTo(point);
            return Double.compare(slope1, slope2);
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}