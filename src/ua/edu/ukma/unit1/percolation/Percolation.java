package ua.edu.ukma.unit1.percolation;


import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {

    private final QuickUnionUF finder;
    private int topSite;
    private int bottomSite;
    private boolean grid[][];
    private int gridSize;

    public Percolation(int gridSize) {
        this.gridSize = gridSize;

        int amountOfSites = gridSize * gridSize;
        finder = new QuickUnionUF(amountOfSites + 2);
        grid = new boolean[amountOfSites][amountOfSites];

        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                grid[i][j] = false;
            }
        }

        topSite = amountOfSites;
        bottomSite = amountOfSites + 1;


    }

    public int getOpenedCount() {
        int openedCount = 0;
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                if (grid[i][j]) {
                    openedCount++;
                }
            }
        }
        return openedCount;
    }


    public void open(int i, int j) {


        grid[i][j] = true;
        if (i == 1) {
            finder.union(convertCoordinatesToNumber(i, j), topSite);
        }

        if (i > 1 && isOpened(i - 1, j)) {
            finder.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i - 1, j));
        }
        if (i < gridSize && isOpened(i + 1, j)) {
            finder.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i + 1, j));
        }
        if (i > 1 && isOpened(i, j - 1)) {
            finder.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i, j - 1));
        }
        if (i < gridSize && isOpened(i, j + 1)) {
            finder.union(convertCoordinatesToNumber(i, j), convertCoordinatesToNumber(i, j + 1));
        }

        if (i == gridSize) {
            finder.union(convertCoordinatesToNumber(i, j), bottomSite);

        }
    }


    public boolean isOpened(int i, int j) {

        return grid[i][j];
    }

    public boolean percolates() {
        return finder.connected(topSite, bottomSite);
    }



    public int convertCoordinatesToNumber(int i, int j) {
        return (j - 1) * gridSize + (i - 1);

    }


}
