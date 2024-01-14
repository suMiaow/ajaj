package com.meme.algs.recursion;

import java.util.Arrays;

/**
 * Memorization
 */
public class E01Fibonacci {

    public static int fibonacci(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return f(n, cache);
    }

    private static int f(int n, int[] cache) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            if (cache[n] != -1) {
                return cache[n];
            } else {
                int result = f(n - 1, cache) + f(n - 2, cache);
                cache[n] = result;
                return result;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(fibonacci(i));
        }
    }
}
