/* *****************************************************************************
 *  Name: Bogdan Sukonnov
 *  Date: 14.03.2020
 *  Description: To perform a series of computational experiments
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("grid size must be more than zero");
        if (trials <= 0) throw new IllegalArgumentException("trials number must be more than zero");
        int nSquare = n * n;
        results = new double[trials];
        int[] indices = getIndices(nSquare);
        for (int trial = 1; trial <= trials; ++trial) {
            Percolation percolation = new Percolation(n);
            StdRandom.shuffle(indices);
            for (int index : indices) {
                percolation.open(1 + index / n, 1 + index % n);
                if (percolation.percolates()) {
                    results[trial - 1] = (double) percolation.numberOfOpenSites() / nSquare;
                    break;
                }
            }
        }
    }

    private int[] getIndices(int nSquare) {
        int[] indices = new int[nSquare];
        for (int index = 0; index < nSquare; ++index) {
            indices[index] = index;
        }
        return indices;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2) throw new IllegalArgumentException("waiting for 2 arguments");
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        StdOut.printf("%-23s = %s\n", "mean", stats.mean());
        StdOut.printf("%-23s = %s\n", "stddev", stats.stddev());
        StdOut.printf("%-23s = [%s, %s]\n", "95% confidence interval", stats.confidenceLo(),
                      stats.confidenceHi());
    }
}
