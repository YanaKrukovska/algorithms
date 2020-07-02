package ua.edu.ukma.ykrukovska.unit6.convexHull;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public final class Point2D implements Comparable<Point2D> {

    public final double x;
    public final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // -1: clockwise, 0 - collinear, 1 - counterclockwise
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0) {
            return -1;
        } else if (area2 > 0) {
            return +1;
        } else {
            return 0;
        }
    }

    public int compareTo(Point2D that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Point2D that = (Point2D) other;
        return this.x == that.x && this.y == that.y;
    }

    public Comparator<Point2D> polarOrder() {
        return new PolarOrder();
    }


    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D point1, Point2D point2) {
            double diffX1 = point1.x - x;
            double diffY1 = point1.y - y;
            double diffX2 = point2.x - x;
            double diffY2 = point2.y - y;

            if (diffY1 >= 0 && diffY2 < 0) {
                // point 1 is above; point 2 is below
                return -1;
            } else if (diffY2 >= 0 && diffY1 < 0) {
                // point 1 below; point 2 above
                return +1;
            } else if (diffY1 == 0 && diffY2 == 0) {
                // 3-collinear and horizontal
                if (diffX1 >= 0 && diffX2 < 0) {
                    return -1;
                } else if (diffX2 >= 0 && diffX1 < 0) {
                    return +1;
                } else return 0;
            } else {
                // both above or below
                return -ccw(Point2D.this, point1, point2);
            }
        }
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + "; " + y + ")";
    }

}
