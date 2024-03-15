package com.meme.algs.dynamicprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BellmanFord {

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = List.of(
                new Edge(6, 5, 9),
                new Edge(4, 5, 6),
                new Edge(1, 6, 14),
                new Edge(3, 6, 2),
                new Edge(3, 4, 11),
                new Edge(2, 4, 15),
                new Edge(1, 3, 9),
                new Edge(1, 2, 7)
        );

        int[] dp = new int[7];
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        print(dp);

        for (int i = 0; i < edges.size() - 1; i++) {
            for (Edge edge : edges) {
                if (dp[edge.from] != Integer.MAX_VALUE) {
                    dp[edge.to] = Math.min(dp[edge.to], dp[edge.from] + edge.weight);
                }
            }
            print(dp);
        }

        print(dp);
    }

    private static void print(int[] dp) {
        System.out.println(
                Arrays.stream(dp).boxed()
                        .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                        .map(s -> String.format("%2s", s))
                        .collect(Collectors.joining(", ", "[", "]"))
        );
    }
}
