package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private final int BLANK = 0;
    private int size;
    private final int[][] board;
    /*
    Constructs a board from an N-by-N array of tiles where tiles[i][j] = tile at row i, column j
     */
    public Board(int[][] tiles) {
        size = tiles.length;
        board = new int[size][size];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }
    private int xyTo1D(int x, int y) {
        return x * size + y;
    }
    /*
    Returns value of tile at row i, column j (or 0 if blank)
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i > size() - 1 || j < 0 || j > size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return board[i][j];
    }
    /*
    Returns the board size N
     */
    public int size() {
        return size;
    }
    /*
    Hamming estimate described below
    Return the number of tiles int the wrong position.
     */
    public int hamming() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] != BLANK && board[i][j] != xyTo1D(i, j) + 1) {
                    count++;
                }
            }
        }
        return count;
    }
    /*
    Manhattan estimate described below
    Return the sum of the Manhattan distances (sum of the vertical and horizontal distance)
    from the tiles to their goal positions
     */
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (board[i][j] != BLANK) {
                    int num = board[i][j];
                    count += Math.abs(rightPosition(num).x - i)
                        + Math.abs((rightPosition(num).y - j));
                }
            }
        }
        return count;
    }
    private class Position {
        private int x;
        private int y;
    }
    private Position rightPosition(int num) {
        Position pos = new Position();
        pos.x = (num - 1) / size();
        pos.y = (num - 1) % size();
        return pos;
    }
    /*
    Returns true if this board's tile values are the same position as y's
     */
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        Board that = (Board) y;
        if (that.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.board[i][j] != that.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    /*
    Estimated distance to goal. This method should
    simply return the results of manhattan() when submitted to Gradescope.
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * Returns the neighbors of the current board.
     *
     * @author http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
