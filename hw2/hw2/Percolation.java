package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private boolean[][] openOrNot;
    private int openNum;
    private int virtualTopSite1;
    private int virtualTopSite2;
    private int virtualBottomSite;
    private WeightedQuickUnionUF uf1;
    private WeightedQuickUnionUF uf2;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N cannot be less than or equal to 0");
        }
        virtualTopSite1 = N * N;
        virtualTopSite2 = N * N;
        virtualBottomSite = N * N + 1;
        uf1 = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
        grid = new int[N][N];
        openOrNot = new boolean[N][N];
        openNum = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            openOrNot[row][col] = true;
            openNum++;
            if (row > 0 && isOpen(row - 1, col)) {
                uf1.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                uf2.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (row < grid.length - 1 && isOpen(row + 1, col)) {
                uf1.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                uf2.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf1.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                uf2.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            if (col < grid[0].length - 1 && isOpen(row, col + 1)) {
                uf1.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                uf2.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
            if (row == 0) {
                uf1.union(xyTo1D(row, col), virtualTopSite1);
                uf2.union(xyTo1D(row, col), virtualTopSite2);
            }
            if (row == grid.length - 1) {
                uf1.union(xyTo1D(row, col), virtualBottomSite);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        return openOrNot[row][col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        return uf2.connected(xyTo1D(row, col), virtualTopSite2);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openNum;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf1.connected(virtualTopSite1, virtualBottomSite);
    }

    // convert grid to line
    private int xyTo1D(int row, int col) {
        return row * grid.length + col;
    }
    public static void main(String[] args) {

    }
}
