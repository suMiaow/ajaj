package com.meme.algs;

import java.util.Arrays;
import java.util.stream.Collectors;

public class KnapsackProblemComplete {


    static class Item {
        int index;
        String name;
        int weight;
        int value;

        public Item(int index, String name, int weight, int value) {
            this.index = index;
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "index=" + index +
                    '}';
        }

        public int unitValue() {
            return value / weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(1, "青铜", 2, 3),
                new Item(2, "白银", 3, 4),
                new Item(3, "黄金", 4, 7)
        };

        System.out.println(select(items, 6));

    }

    private static int select(Item[] items, int total) {
        int[] dp = new int[total + 1];

        print(dp);
        for (Item item : items) {
            for (int j = 0; j <= total; j++) {
                if (j >= item.weight) {
                    dp[j] = Math.max(dp[j], item.value + dp[j - item.weight]);
                }
            }
            print(dp);
        }
        return dp[total];
    }
    private static void print(int[][] dist) {
        System.out.println("------------------------");
        for (int[] row : dist) {
            System.out.println(
                    Arrays.stream(row).boxed()
                            .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                            .map(s -> String.format("%2s", s))
                            .collect(Collectors.joining(", ", "[", "]"))
            );
        }
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
