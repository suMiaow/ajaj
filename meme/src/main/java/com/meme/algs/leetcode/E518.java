package com.meme.algs.leetcode;

import java.util.LinkedList;

public class E518 {

    public static int change(int amount, int[] coins) {
        return rec(coins.length - 1, coins, amount, new LinkedList<>());
    }

    private static int rec(int index, int[] coins, int remainder, LinkedList<Integer> stack) {
        if (remainder < 0) {
            System.out.println("N: " + stack);
            return 0;
        } else if (remainder == 0) {
            System.out.println("Y: " + stack);
            return 1;
        } else {
            int count = 0;
            for (int i = index; i >= 0; i--) {
                stack.push(coins[i]);
                count += rec(i, coins, remainder - coins[i], stack);
                stack.pop();
            }
            return count;
        }

    }

    public static void main(String[] args) {
        int result = change(5, new int[]{1, 2, 5});
        System.out.println(result);
    }
}
