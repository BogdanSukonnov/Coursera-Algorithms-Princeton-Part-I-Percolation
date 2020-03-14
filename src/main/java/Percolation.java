import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {

    private int rowsNumber;
    private WeightedQuickUnionUF graph;
    private boolean[][] sites;
    private int openNumber = 0;
    private int topRootIndex;
    private int bottomRootIndex;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("grid size must be more than zero");
        this.rowsNumber = n;
        this.sites = new boolean[n][n];
        int nSquare = n * n;
        this.graph = new WeightedQuickUnionUF(2 + nSquare);
        this.topRootIndex = nSquare;
        this.bottomRootIndex = 1 + nSquare;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        ++openNumber;
        sites[row - 1][col - 1] = true;
        // union with open top
        unionWithOpenIfPossible(row, col, row - 1, col);
        // union with open right
        unionWithOpenIfPossible(row, col, row, col + 1);
        // union with open bottom
        unionWithOpenIfPossible(row, col, row + 1, col);
        // union with open left
        unionWithOpenIfPossible(row, col, row, col - 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        return graph.find(graphIndex(row, col)) == graph.find(topRootIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openNumber;
    }

    // does the system percolate?
    public boolean percolates() {
        return graph.find(topRootIndex) == graph.find(bottomRootIndex);
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    private void validate(int row, int col) {
        if (row > rowsNumber)
            throw new IllegalArgumentException("row can't be more than size = " + rowsNumber);
        if (col > rowsNumber)
            throw new IllegalArgumentException("column can't be more than size = " + rowsNumber);
    }

    private int graphIndex(int row, int col) {
        if (row == 0) return topRootIndex;
        else if (row == rowsNumber + 1) return bottomRootIndex;
        return (col - 1) + (rowsNumber * (row - 1));
    }

    private void unionWithOpenIfPossible(int row1, int col1, int row2, int col2) {
        if (col2 < 1 || col2 > rowsNumber) return;
        if (row2 != 0 && row2 != rowsNumber + 1) {
            // top and bottom root allways open.
            // row2 != 0 - is top root; row2 != rowsNumber + 1 - is bottom root
            if (!isOpen(row2, col2)) return;
        }
        graph.union(graphIndex(row1, col1), graphIndex(row2, col2));
    }
}
