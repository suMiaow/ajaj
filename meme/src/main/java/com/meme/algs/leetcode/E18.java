package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class E18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 4, target, 0, nums.length, new LinkedList<>(), result);
        return result;
    }

    static void dfs(int[] nums, int n, long target, int i, int j, LinkedList<Integer> stack, List<List<Integer>> result) {

        if (n == 2) {

            twoSum(nums, target, i, j, stack, result);
            return;
        }

        for (int k = i; k < j; k++) {
            int num = nums[k];
            if (k > i && nums[k - 1] == num) {
                continue;
            }
            stack.push(num);
            dfs(nums, n - 1, target - num, k + 1, j, stack, result);
            stack.pop();
        }
    }

    static void twoSum(int[] numbers, long target, int i, int j, LinkedList<Integer> stack, List<List<Integer>> result) {

        j--;
        while (i < j) {
            int a = numbers[i];
            int b = numbers[j];
            int sum = a + b;
            if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            } else {
                stack.push(a);
                stack.push(b);
                result.add(new ArrayList<>(stack));
                stack.pop();
                stack.pop();
                do {
                    i++;
                } while (i < j && a == numbers[i]);
                do {
                    j--;
                } while (i < j && b == numbers[j]);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = fourSum(new int[]{1,0,-1,0,-2,2}, 0);
        System.out.println(result);
    }
}
