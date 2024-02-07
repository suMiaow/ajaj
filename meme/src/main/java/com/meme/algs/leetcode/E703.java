package com.meme.algs.leetcode;

public class E703 {

    private int size;
    private final int k;

    public E703(int k, int[] nums) {

        this.array = new int[k];
        this.k = k;
        for (int i = 0; i < k && i < nums.length; i++) {
            offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > peek()) {
                replace(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (size < k) {
            offer(val);
        } else if (val > peek()) {
            replace(val);
        }
        return peek();
    }

    int[] array;

    public boolean offer(int offered) {
        array[size++] = offered;
        up(size - 1);
        return true;
    }

    private void up(int i) {
        if (i <= 0) {
            return;
        }
        int p;
        do {
            p = (i - 1) / 2;
            if (array[p] > array[i]) {
                swap(p, i);
                i = p;
            } else {
                break;
            }
        } while (i > 0);
    }

    public int peek() {
        return array[0];
    }

    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    private void down(int p) {
        int maxp = size / 2 - 1;
        if (p > maxp) {
            return;
        }
        do {
            int l = p * 2 + 1;
            int r = p * 2 + 2;
            int min = p;
            if (r < size) {
                if (array[l] < array[p] && array[l] <= array[r]) {
                    min = l;
                } else if (array[r] < array[p] && array[r] <= array[l]) {
                    min = r;
                }
            } else {
                if (array[l] < array[p]) {
                    min = l;
                }
            }
            if (min != p) {
                swap(p, min);
                p = min;
            } else {
                break;
            }
        } while (p <= maxp);

    }

    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
