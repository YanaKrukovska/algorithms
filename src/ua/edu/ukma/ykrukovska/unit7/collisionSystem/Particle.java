package ua.edu.ukma.ykrukovska.unit7.collisionSystem;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;


public class Particle {
    private static final double INFINITY = Double.POSITIVE_INFINITY;
    private double rx, ry;
    private final int rColor = 255;
    private int gColor = 255;
    private int bColor = 255;
    private double vx, vy;
    private int count;
    private final double radius;
    private final double mass;
    private Color color;

    public Particle() {
        rx = StdRandom.uniform(0.0, 1.0);
        ry = StdRandom.uniform(0.0, 1.0);
        vx = StdRandom.uniform(-0.01, 0.01);
        vy = StdRandom.uniform(-0.01, 0.01);
        radius = 0.02;
        mass = 0.5;
        color = new Color(rColor, gColor, bColor);
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    public int count() {
        return count;
    }

    public double timeToHit(Particle that) {
        if (this == that) {
            return INFINITY;
        }
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        if (dvdr > 0) {
            return INFINITY;
        }
        double dvdv = dvx * dvx + dvy * dvy;
        if (dvdv == 0) {
            return INFINITY;
        }
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0) {
            return INFINITY;
        }
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
        if (vx > 0) {
            return (1.0 - rx - radius) / vx;
        } else if (vx < 0) {
            return (radius - rx) / vx;
        } else return INFINITY;
    }

    public double timeToHitHorizontalWall() {
        if (vy > 0) return (1.0 - ry - radius) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        else return INFINITY;
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;

        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

        double jx = J * dx / dist;
        double jy = J * dy / dist;

        this.vx += (jx / this.mass);
        this.vy += (jy / this.mass);
        that.vx -= (jx / that.mass);
        that.vy -= (jy / that.mass);

        changeColor(that);

        this.count++;
        that.count++;
    }

    private void changeColor(Particle that) {
        if (this.bColor - 5 >= 0 && this.gColor - 5 >= 0) {
            this.bColor -= 5;
            this.gColor -= 5;
            this.color = new Color(this.rColor, this.gColor, this.bColor);
        }
        if (that != null && that.bColor - 5 >= 0 && that.gColor - 5 >= 0) {
            that.bColor -= 5;
            that.gColor -= 5;
            that.color = new Color(that.rColor, that.gColor, that.bColor);
        }
    }

    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
        changeColor(null);
    }

    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
        changeColor(null);
    }

}

