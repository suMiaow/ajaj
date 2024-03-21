package com.meme.algs.dynamicprogramming;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TravellingSalesmanProblem {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 2, 3},
                {1, 0, 6, 4},
                {2, 6, 0, 5},
                {3, 4, 5, 0},
        };
        System.out.println(tsp(graph));

    }

    static int tsp(int[][] g) {
        int m = g.length;
        int n = 1 << m - 1;
        int[][] dp = new int[m][n];

        for (int k = 0; k < m; k++) {
            dp[k][0] = g[k][0];
        }
        print(dp);
        for (int j = 1; j < n; j++) {
            for (int k = 0; k < m; k++) {
                dp[k][j] = Integer.MAX_VALUE / 2;
                if (contains(j, k)) {
                    continue;
                }
                for (int i = 1; i < m; i++) {
                    if (contains(j, i)) {
                        dp[k][j] = Math.min(dp[k][j], g[k][i] + dp[i][exclude(j, i)]);
                    }
                }
            }
        }
        print(dp);

        return dp[0][n - 1];
    }

    static boolean contains(int set, int city) {
        return (set >> (city - 1) & 1) == 1;
    }

    static int exclude(int set, int city) {
        return set ^ (1 << (city - 1));
    }

    private static void print(int[][] dist) {
        System.out.println("------------------------");
        for (int[] row : dist) {
            System.out.println(
                    Arrays.stream(row).boxed()
                            .map(x -> x == Integer.MAX_VALUE / 2 ? "∞" : String.valueOf(x))
                            .map(s -> String.format("%3s", s))
                            .collect(Collectors.joining(", ", "[", "]"))
            );
        }
    }
}
