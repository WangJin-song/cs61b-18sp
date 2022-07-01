package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] openNums;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        openNums = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                // Returns a random integer uniformly in [0, n)
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            openNums[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openNums);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openNums);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.pow(openNums.length, 0.5);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.pow(openNums.length, 0.5);
    }
}
