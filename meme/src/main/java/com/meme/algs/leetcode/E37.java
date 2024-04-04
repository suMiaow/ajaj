package com.meme.algs.leetcode;


public class E37 {
    public static void solveSudoku(char[][] board) {

        boolean[][] cr = new boolean[9][9];
        boolean[][] cc = new boolean[9][9];
        boolean[][] cb = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    int index = ch - '1';
                    cr[i][index] = true;
                    cc[j][index] = true;
                    cb[(i / 3) * 3 + (j / 3)][index] = true;
                }
            }
        }

        dfs(board, cr, cc, cb, 0, 0);

    }

    public static boolean dfs(char[][] board, boolean[][] cr, boolean[][] cc, boolean[][] cb, int i, int j) {

        while (board[i][j] != '.') {
            if (++j >= 9) {
                j = 0;
                i++;
            }
            if (i >= 9) {
                return true;
            }
        }

        for (int k = 0; k < 9; k++) {
            if (!cr[i][k] && !cc[j][k] && !cb[(i / 3) * 3 + (j / 3)][k]) {
                board[i][j] = (char) ('0' + 1 + k);
                if (i == 8 && j == 8) {
                    return true;
                }
                cr[i][k] = true;
                cc[j][k] = true;
                cb[(i / 3) * 3 + (j / 3)][k] = true;

                if (!dfs(board, cr, cc, cb, i, j)) {
                    cr[i][k] = false;
                    cc[j][k] = false;
                    cb[(i / 3) * 3 + (j / 3)][k] = false;
                    board[i][j] = '.';
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private static void print(char[][] dist) {
        System.out.println("------------------------");
        for (char[] row : dist) {
            for (char c : row) {
                System.out.printf("%2s", c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        print(board);

        solveSudoku(board);

        print(board);
    }
}
