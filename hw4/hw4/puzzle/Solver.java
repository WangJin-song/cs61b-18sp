package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;
/**
 * @author Marcus
 * @create 2022-07-10 16:27
 */
public class Solver {
    private SearchNode end;
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState ws;
        private int movesTillNow;
        private SearchNode prev;
        private SearchNode(WorldState initial) {
            ws = initial;
            movesTillNow = 0;
            prev = null;
        }
        private SearchNode(WorldState ws, int movesTillNow, SearchNode prev) {
            this.ws = ws;
            this.movesTillNow = movesTillNow;
            this.prev = prev;
        }
        @Override
        public int compareTo(SearchNode o) {
            int curNum = this.movesTillNow + this.ws.estimatedDistanceToGoal();
            int oNum = o.movesTillNow + o.ws.estimatedDistanceToGoal();
            return curNum - oNum;
        }
    }
    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial));
        SearchNode min = pq.delMin();
        WorldState curr = min.ws;
        while (!curr.isGoal()) {
            for (WorldState next : curr.neighbors()) {
                if (min.prev == null || !next.equals(min.prev.ws)) {
                    pq.insert(new SearchNode(next, min.movesTillNow + 1, min));
                }
            }
            min = pq.delMin();
            curr = min.ws;
        }
        end = min;
    }
    public int moves() {
        return end.movesTillNow;
    }
    public Iterable<WorldState> solution() {
        LinkedList<WorldState> list = new LinkedList<>();
        SearchNode ptr = end;
        while (ptr != null) {
            list.addFirst(ptr.ws);
            ptr = ptr.prev;
        }
        return list;
    }
}
