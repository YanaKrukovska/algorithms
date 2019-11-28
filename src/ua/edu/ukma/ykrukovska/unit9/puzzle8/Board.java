package ua.edu.ukma.ykrukovska.unit9.puzzle8;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board {

    private final int[][] blocks;

    public Board(int[][] cells) {

        this.blocks = copy(cells);
    }

    private int[][] copy(int[][] blocksToCopy) {
        int[][] copy = new int[blocksToCopy.length][blocksToCopy.length];
        for (int i = 0; i < blocksToCopy.length; i++) {
            for (int j = 0; j < blocksToCopy.length; j++) {
                copy[i][j] = blocksToCopy[i][j];
            }
        }
        return copy;
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension()).append("\n");
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                stringBuilder.append(String.format("%2d ", blocks[i][j]));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int hammingCount = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * dimension() + j + 1) {
                    hammingCount++;
                }
            }
        }
        return hammingCount;
    }


    public int manhattan() {
        int manhattanCount = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != i * dimension() + j + 1) {
                    int currentNumber = blocks[i][j] - 1;
                    manhattanCount += Math.abs(currentNumber % dimension() - j) + Math.abs(currentNumber / dimension() - i);

                }
            }
        }

        return manhattanCount;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        return Arrays.deepEquals(this.blocks, that.blocks);
    }


    private void swap(int[][] blocksArray, int i, int j, int m, int k) {
        int temp = blocksArray[i][j];
        blocksArray[i][j] = blocksArray[m][k];
        blocksArray[m][k] = temp;
    }

    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<>();
        int blankRow = 0, blankCol = 0;
        for (int i = 0; i < dimension(); i++)
            for (int j = 0; j < blocks[i].length; j++)
                if (blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
        if (blankRow > 0 && blankRow < dimension()) {
            int[][] blocksCopy = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++)
                blocksCopy[i] = blocks[i].clone();
            swap(blocksCopy, blankRow, blankCol, blankRow - 1, blankCol);
            Board neighborBlock = new Board(blocksCopy);
            neighbors.enqueue(neighborBlock);
        }

        if (blankRow < dimension() - 1) {
            int[][] blocksCopy = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++)
                blocksCopy[i] = blocks[i].clone();
            swap(blocksCopy, blankRow, blankCol, blankRow + 1, blankCol);
            Board neighborBlock = new Board(blocksCopy);
            neighbors.enqueue(neighborBlock);
        }

        if (blankCol > 0) {
            int[][] blocksCopy = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++)
                blocksCopy[i] = blocks[i].clone();
            swap(blocksCopy, blankRow, blankCol, blankRow, blankCol - 1);
            Board neighborBlock = new Board(blocksCopy);
            neighbors.enqueue(neighborBlock);
        }
        if (blankCol < dimension() - 1) {
            int[][] blocksCopy = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++)
                blocksCopy[i] = blocks[i].clone();
            swap(blocksCopy, blankRow, blankCol, blankRow, blankCol + 1);
            Board neighborBlock = new Board(blocksCopy);
            neighbors.enqueue(neighborBlock);
        }
        return neighbors;
    }

    public Board twin() {
        int[][] twinBoard = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++)
            twinBoard[i] = blocks[i].clone();
        if (twinBoard[0][0] != 0 && twinBoard[0][1] != 0) {
            swap(twinBoard, 0, 0, 0, 1);
        } else {
            swap(twinBoard, 1, 0, 1, 1);
        }
        return new Board(twinBoard);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 5}});
        System.out.println(board.toString());
    }

}