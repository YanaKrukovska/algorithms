package ua.edu.ukma.ykrukovska.unit1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF quickUnion;
    private final int topSite;
    private final int bottomSite;
    private boolean[][] grid;
    private final int gridSize;

    public Percolation(int gridSize) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException();
        }

        this.gridSize = gridSize;

        quickUnion = new WeightedQuickUnionUF(gridSize * gridSize + 2);
        grid = new boolean[gridSize * gridSize][gridSize * gridSize];

        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                grid[i - 1][j - 1] = false;
            }
        }
        topSite = gridSize * gridSize;
        bottomSite = gridSize * gridSize + 1;
    }


    public int numberOfOpenSites() {
        int openedCount = 0;
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                if (grid[i - 1][j - 1]) {
                    openedCount++;
                }
            }
        }
        return openedCount;
    }


    public void open(int i, int j) {
        validate(i, j);
        grid[i - 1][j - 1] = true;
        if (i == 1) {
            quickUnion.union(convertCoordinatesToNumber(i, j), topSite);
        }
        if (i > 1 && isOpen(i - 1, j)) {
            quickUnion.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i - 1, j));
        }
        if (i < gridSize && i + 1 <= gridSize && isOpen(i + 1, j)) {
            quickUnion.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i + 1, j));
        }
        if (i > 1 && j - 1 >= 1 && isOpen(i, j - 1)) {
            quickUnion.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i, j - 1));
        }
        if (i < gridSize && j + 1 <= gridSize && isOpen(i, j + 1)) {
            quickUnion.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i, j + 1));
        }
        if (i == gridSize) {
            quickUnion.union(convertCoordinatesToNumber(i, j), bottomSite);
        }
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        return quickUnion.connected(topSite, convertCoordinatesToNumber(i, j));
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean percolates() {
        return quickUnion.connected(topSite, bottomSite);
    }

    private int convertCoordinatesToNumber(int i, int j) {
        return (j - 1) * gridSize + (i - 1);
    }

    private void validate(int i, int j) {
        if (i < 1 || i > gridSize || j < 1 || j > gridSize) {
            throw new IllegalArgumentException();
        }
    }
}
