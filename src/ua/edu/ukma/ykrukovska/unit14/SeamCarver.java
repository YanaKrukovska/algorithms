package ua.edu.ukma.ykrukovska.unit14;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private int[][] colors;

    private SeamCarver(Picture picture) {
        checkIfNull(picture == null);
        colors = new int[picture.width()][picture.height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                colors[i][j] = picture.get(i, j).getRGB();
            }
        }
    }

    private Picture picture() {
        Picture picture = new Picture(colors.length, colors[0].length);
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[0].length; j++) {
                Color color = new Color(this.colors[i][j]);
                picture.set(i, j, color);
            }
        }
        return picture;
    }

    private int width() {
        return this.colors.length;
    }

    private int height() {
        return this.colors[0].length;
    }

    private double energy(int x, int y) {
        if (x < 0 || x > this.width() - 1 || y < 0 || y > this.height() - 1) {
            throw new IllegalArgumentException();
        }
        if (x == 0 || x == this.width() - 1 || y == 0 || y == this.height() - 1) {
            return 1000.0;
        } else {
            int xRed = getRed(colors[x - 1][y]) - getRed(colors[x + 1][y]);
            int xGreen = getGreen(colors[x - 1][y]) - getGreen(colors[x + 1][y]);
            int xBlue = getBlue(colors[x - 1][y]) - getBlue(colors[x + 1][y]);

            int yRed = getRed(colors[x][y - 1]) - getRed(colors[x][y + 1]);
            int yGreen = getGreen(colors[x][y - 1]) - getGreen(colors[x][y + 1]);
            int yBlue = getBlue(colors[x][y - 1]) - getBlue(colors[x][y + 1]);

            return Math.sqrt(Math.pow(xRed, 2) + Math.pow(xBlue, 2) + Math.pow(xGreen, 2) + Math.pow(yRed, 2) + Math.pow(yBlue, 2) + Math.pow(yGreen, 2));
        }

    }


    private int[] findVerticalSeam() {
        int n = this.width() * this.height();
        int[] seam = new int[this.height()];
        int[] nodeTo = new int[n];
        double[] distanceTo = new double[n];
        for (int i = 0; i < n; i++) {
            if (i < width()) {
                distanceTo[i] = 0;
            } else {
                distanceTo[i] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                for (int shift = -1; shift <= 1; shift++) {
                    if (!(j + shift < 0 || j + shift > this.width() - 1 || i + 1 > this.height() - 1)) {
                        if (distanceTo[index(j + shift, i + 1)] > distanceTo[index(j, i)] + energy(j, i)) {
                            distanceTo[index(j + shift, i + 1)] = distanceTo[index(j, i)] + energy(j, i);
                            nodeTo[index(j + shift, i + 1)] = index(j, i);
                        }
                    }
                }
            }
        }

        // find min distance in the last row
        double min = Double.POSITIVE_INFINITY;
        int index = -1;
        for (int j = 0; j < width(); j++) {
            if (distanceTo[j + width() * (height() - 1)] < min) {
                index = j + width() * (height() - 1);
                min = distanceTo[j + width() * (height() - 1)];
            }
        }

        // find seam one by one
        for (int j = 0; j < height(); j++) {
            int y = height() - j - 1;
            int x = index - y * width();
            seam[height() - 1 - j] = x;
            index = nodeTo[index];
        }

        return seam;
    }

    private void removeHorizontalSeam(int[] seam) {
        checkIfNull(height() <= 1);
        checkIfNull(seam == null);
        checkIfNull(seam.length != width());

        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] > height() - 1)
                throw new IllegalArgumentException();
            if (i < width() - 1 && Math.pow(seam[i] - seam[i + 1], 2) > 1)
                throw new IllegalArgumentException();
        }

        int[][] updatedColor = new int[width()][height() - 1];
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] == 0) {
                System.arraycopy(this.colors[i], seam[i] + 1, updatedColor[i], 0, height() - 1);
            } else if (seam[i] == height() - 1) {
                System.arraycopy(this.colors[i], 0, updatedColor[i], 0, height() - 1);
            } else {
                System.arraycopy(this.colors[i], 0, updatedColor[i], 0, seam[i]);
                System.arraycopy(this.colors[i], seam[i] + 1, updatedColor[i], seam[i], height() - seam[i] - 1);
            }

        }
        this.colors = updatedColor;
    }

    private void removeVerticalSeam(int[] seam) {
        this.colors = transpose(this.colors);
        removeHorizontalSeam(seam);
        this.colors = transpose(this.colors);
    }


    private int[] findHorizontalSeam() {
        this.colors = transpose(this.colors);
        int[] seam = findVerticalSeam();
        this.colors = transpose(this.colors);
        return seam;
    }


    private int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    private int getBlue(int rgb) {
        return (rgb) & 0xFF;
    }

    private int index(int x, int y) {
        return width() * y + x;
    }

    private int[][] transpose(int[][] origin) {
        checkIfNull(origin == null);
        checkIfNull(origin.length < 1);
        int[][] result = new int[origin[0].length][origin.length];
        for (int i = 0; i < origin[0].length; i++) {
            for (int j = 0; j < origin.length; j++) {
                result[i][j] = origin[j][i];
            }
        }
        return result;
    }

    private void checkIfNull(boolean element) {
        if (element) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        Picture pic = new Picture("D:/Studying/Algorithms/Practice14/test.jpg");
        pic.show();

        SeamCarver seamCarver = new SeamCarver(pic);
        for (int i = 0; i < 200; i++) {
            int[] verticalSeam = seamCarver.findVerticalSeam();
            seamCarver.removeVerticalSeam(verticalSeam);
        }
        seamCarver.picture().show();

        SeamCarver seamCarver2 = new SeamCarver(pic);
        for (int i = 0; i < 200; i++) {
            int[] horizontalSeam = seamCarver2.findHorizontalSeam();
            seamCarver2.removeHorizontalSeam(horizontalSeam);

        }
        seamCarver2.picture().show();

        SeamCarver seamCarver3 = new SeamCarver(pic);
        for (int i = 0; i < 100; i++) {
            int[] verticalSeam = seamCarver3.findVerticalSeam();
            seamCarver3.removeVerticalSeam(verticalSeam);
            int[] horizontalSeam = seamCarver3.findHorizontalSeam();
            seamCarver3.removeHorizontalSeam(horizontalSeam);
        }
        seamCarver3.picture().show();
    }
}