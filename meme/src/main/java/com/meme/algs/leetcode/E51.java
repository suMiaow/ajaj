package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E51 {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }
        char[][] table = new char[n][n];

        boolean[] col = new boolean[n];
        boolean[] lDia = new boolean[2 * n - 1];
        boolean[] rDia = new boolean[2 * n - 1];

        for (char[] chars : table) {
            Arrays.fill(chars, '.');
        }
        dfs(n, result, 0, table, col, lDia, rDia);

        return result;
    }

    public static void dfs(int n,
                           List<List<String>> result,
                           int i,
                           char[][] table,
                           boolean[] col,
                           boolean[] lDia,
                           boolean[] rDia) {

        if (i == n) {
            List<String> list = new ArrayList<>();
            for (char[] chars : table) {
                list.add(new String(chars));
            }
            result.add(list);
            return;
        }


        for (int j = 0; j < n; j++) {
            if (col[j] || lDia[i + j] || rDia[n - 1 - i + j]) {
                continue;
            }
            table[i][j] = 'Q';
            col[j] = lDia[i + j] = rDia[n - 1 - i + j] = true;
            dfs(n, result, i + 1, table, col, lDia, rDia);
            col[j] = lDia[i + j] = rDia[n - 1 - i + j] = false;
            table[i][j] = '.';
        }


    }

    public static void main(String[] args) {
        solveNQueens(4);
    }
}
