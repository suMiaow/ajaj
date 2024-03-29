package com.meme.algs.leetcode;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class E322 {

    static int min = -1;

    public static int coinChange(int[] coins, int amount) {

        if (coins.length < 1 && amount <= 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j], 1 + dp[j - coin]);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    private static void rec(int index, int[] coins, int remainder, AtomicInteger count, LinkedList<Integer> stack) {
        if (remainder < 0) {
            System.out.println("N: " + stack);
        } else if (remainder == 0) {
            System.out.println("Y: " + stack);
            if (min == -1) {
                min = count.get();
            } else {
                min = Math.min(min, count.get());
            }
        } else {
            for (int i = index; i >= 0; i--) {
                stack.push(coins[i]);
                count.incrementAndGet();
                rec(i, coins, remainder - coins[i], count, stack);
                stack.pop();
                count.decrementAndGet();
            }
        }

    }


    public static int coinChangeGreedy(int[] coins, int amount) {

        int remainder = amount;
        int count = 0;
        for (int coin : coins) {
            while (remainder >= coin) {
                remainder -= coin;
                count++;
            }
            if (remainder == 0) {
                break;
            }
        }
        if (remainder > 0) {
            return -1;
        } else {
            return count;
        }
    }

    public static void main(String[] args) {
        int result = coinChange(new int[]{2, 5, 10, 1}, 27);
        System.out.println(result);
    }
}
