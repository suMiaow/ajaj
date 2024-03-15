package com.meme.algs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class KnapsackProblem {

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
                new Item(1, "黄金", 4, 1600),
                new Item(2, "宝石", 8, 2400),
                new Item(3, "白银", 5, 30),
                new Item(4, "钻石", 1, 10_000)
        };

        int result = select(items, 10);
        System.out.println(result);

    }

    static int selectGreedy(Item[] items, int total) {
        Arrays.sort(items, Comparator.comparingInt(Item::unitValue).reversed());

        int result = 0;

        for (Item item : items) {
            if (total >= item.weight) {
                total -= item.weight;
                result += item.value;
            }
        }
        return result;
    }

    static int select(Item[] items, int total) {
        int[] dp = new int[total + 1];

        Item item0 = items[0];
        for (int i = 0; i <= total; i++) {
            if (i >= item0.weight) {
                dp[i] = item0.value;
            } else {
                dp[i] =  0;
            }
        }
        System.out.println(Arrays.toString(dp));
        for (int i = 1; i < items.length; i++) {
            Item item = items[i];
            for (int j = total; j > 0; j--) {
                if (j >= item.weight) {
                    dp[j] = Math.max(dp[j], item.value + dp[j - item.weight]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }

        return dp[total];
    }

    private static void print(int[][] dist) {
        System.out.println("------------------------");
        for (int[] row : dist) {
            System.out.println(
                    Arrays.stream(row).boxed()
                            .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                            .map(s -> String.format("%5s", s))
                            .collect(Collectors.joining(", ", "[", "]"))
            );
        }
    }
}
