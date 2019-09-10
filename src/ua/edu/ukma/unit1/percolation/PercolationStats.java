package ua.edu.ukma.unit1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int gridDimension;
    private int amountOfExperiments;
    private double[] results;
    private int n = 0;

    private PercolationStats(int n, int T) {
        this.gridDimension = n;
        this.amountOfExperiments = T;


        results = new double[amountOfExperiments];
        for (int i = 0; i < amountOfExperiments; i++) {
            results[i] = doExperiment();
        }

    }

    private double doExperiment() {

        Percolation percolation = new Percolation(gridDimension);
        int numberOfSites = gridDimension * gridDimension;

        do {
            int siteToOpen;
            do {
                siteToOpen = StdRandom.uniform(numberOfSites);
            } while (percolation.isOpened(getRow(siteToOpen), getColumn(siteToOpen)));
            percolation.open(getRow(siteToOpen), getColumn(siteToOpen));
        } while (!percolation.percolates());

        return (double) percolation.getOpenedCount() / (gridDimension * gridDimension);

    }

    private double mean() {
        return StdStats.mean(results);
    }

    private double stddev() {
        return StdStats.stddev(results);
    }

    private int getRow(int point) {
        return (point / gridDimension) + 1;
    }

    private int getColumn(int point) {
        return (point % gridDimension) + 1;
    }

    private double confidenceLow(){
        return StdStats.mean(results) - ((1.96 * StdStats.stddev(results))/Math.sqrt(amountOfExperiments));
    }

    private double confidenceHigh(){
        return StdStats.mean(results) + ((1.96 * StdStats.stddev(results))/Math.sqrt(amountOfExperiments));
    }

    public static void main(String[] args) {

        int n = 20;
        int T = 30;
        PercolationStats stats = new PercolationStats(n, T);
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval low = " + stats.confidenceLow());
        System.out.println("95% confidence interval high = " + stats.confidenceHigh());


    }

}
