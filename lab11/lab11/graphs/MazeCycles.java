package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int mark;
    private Maze maze;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = m.xyTo1D(1, 1);
    }

    @Override
    public void solve() {
        detectCycle(s);
    }

    // Helper methods go here
    private void detectCycle(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                detectCycle(w);
                if (w == mark) {
                    edgeTo[v] = v;
                    mark = v;
                }
                announce();
                return;
            } else if (marked[w] && edgeTo[w] != edgeTo[edgeTo[v]] && edgeTo[w] != Integer.MAX_VALUE) {
                edgeTo[w] = v;
                mark = w;
                return;
            }
        }
    }
}

