package ua.edu.ukma.ykrukovska.unit9.puzzle8;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private SearchNode lastNode;

    private class SearchNode implements Comparable<SearchNode> {

        private SearchNode previous;
        private final Board board;
        private int amountOfMoves = 0;

        public SearchNode(Board board) {
            this.board = board;
        }

        public SearchNode(Board board, SearchNode previous) {
            this.previous = previous;
            this.board = board;
            this.amountOfMoves = previous.amountOfMoves + 1;
        }

        @Override
        public int compareTo(SearchNode that) {
            return (this.board.manhattan() - that.board.manhattan()) + (this.amountOfMoves - that.amountOfMoves);
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        MinPQ<SearchNode> queue = new MinPQ<>();
        queue.insert(new SearchNode(initial));

        MinPQ<SearchNode> twinMoves = new MinPQ<SearchNode>();
        twinMoves.insert(new SearchNode(initial.twin()));

        while (true) {
            lastNode = expand(queue);
            if (lastNode != null || expand(twinMoves) != null) return;
        }
    }

    private SearchNode expand(MinPQ<SearchNode> moves) {
        if (moves.isEmpty()) {
            return null;
        }
        SearchNode bestMove = moves.delMin();
        if (bestMove.board.isGoal()) {
            return bestMove;
        }
        for (Board neighbor : bestMove.board.neighbors()) {
            if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
                moves.insert(new SearchNode(neighbor, bestMove));
            }
        }
        return null;
    }

    public boolean isSolvable() {
        return lastNode != null;
    }

    public int moves() {
        return isSolvable() ? lastNode.amountOfMoves : -1;
    }

    public Iterable<Board> solution() {

        if (!isSolvable()) {
            return null;
        }

        Stack<Board> moves = new Stack<Board>();
        while (lastNode != null) {
            moves.push(lastNode.board);
            lastNode = lastNode.previous;
        }

        return moves;
    }

    public static void main(String[] args) {

        In in = new In("D:/Studying/Algorithms/puzzle/puzzle4x4.txt");

        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}