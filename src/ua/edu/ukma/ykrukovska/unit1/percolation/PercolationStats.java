package ua.edu.ukma.ykrukovska.unit1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int gridDimension;
    private final int amountOfExperiments;
    private final double[] results;

    public PercolationStats(int n, int t) {

        if (n < 1 || t < 1){
            throw new IllegalArgumentException();
        }

        this.gridDimension = n;
        this.amountOfExperiments = t;

        results = new double[amountOfExperiments];
        for (int i = 0; i < amountOfExperiments; i++) {
            results[i] = doExperiment();
        }
    }

    private double doExperiment() {

        Percolation percolation = new Percolation(gridDimension);
        int numberOfSites = gridDimension * gridDimension;

        while (!percolation.percolates()){
            int siteToOpen;
            do {
                siteToOpen = StdRandom.uniform(numberOfSites);
            } while (percolation.isOpen(getRow(siteToOpen), getColumn(siteToOpen)));
            percolation.open(getRow(siteToOpen), getColumn(siteToOpen));
        }

        return (double) percolation.numberOfOpenSites() / (numberOfSites);
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    private int getRow(int point) {
        return (point / gridDimension) + 1;
    }

    private int getColumn(int point) {
        return (point % gridDimension) + 1;
    }

    public double confidenceLo() {
        return StdStats.mean(results) - ((CONFIDENCE_95 * StdStats.stddev(results)) / Math.sqrt(amountOfExperiments));
    }

    public double confidenceHi() {
        return StdStats.mean(results) + ((CONFIDENCE_95 * StdStats.stddev(results)) / Math.sqrt(amountOfExperiments));
    }

    public static void main(String[] args) {
    }

}
