package com.meme.algs.leetcode;

import java.util.PriorityQueue;

public class E703_2 {

    private PriorityQueue<Integer> pq;
    private final int k;

    public E703_2(int k, int[] nums) {

        this.pq = new PriorityQueue<>(k);
        this.k = k;
        for (int num : nums) {
            pq.add(num);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

}
