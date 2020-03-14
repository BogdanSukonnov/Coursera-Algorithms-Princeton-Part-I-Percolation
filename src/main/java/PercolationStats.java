/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("grid size must be more than zero");
        if (trials <= 0) throw new IllegalArgumentException("trials number must be more than zero");
    }

    // sample mean of percolation threshold
    public double mean() {
        return  0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

    // test client (see below)
    public static void main(String[] args) {
        /** takes two command-line arguments n and T,
         * performs T independent computational experiments (discussed above)
         * on an n-by-n grid, and prints the sample mean, sample standard deviation,
         * and the 95% confidence interval for the percolation threshold.
         * Use StdRandom to generate random numbers;
         * use StdStats to compute the sample mean and sample standard deviation.         *
         */
    }
}
