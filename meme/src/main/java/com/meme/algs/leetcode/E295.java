package com.meme.algs.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class E295 {

    private final PriorityQueue<Integer> minPq;
    private final PriorityQueue<Integer> maxPq;

    public E295() {
        minPq = new PriorityQueue<>();
        maxPq = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if (minPq.isEmpty() && maxPq.isEmpty()) {
            minPq.offer(num);
        } else {
            if (num > minPq.peek()) {
                minPq.offer(num);
            } else {
                maxPq.offer(num);
            }
        }

        if (minPq.size() - maxPq.size() > 1) {
            maxPq.offer(minPq.poll());
        }
        if (maxPq.size() - minPq.size() > 1) {
            minPq.offer(maxPq.poll());
        }
    }

    public double findMedian() {
        if (minPq.size() > maxPq.size()) {
            return minPq.peek();
        } else if (minPq.size() == maxPq.size()) {
            return (minPq.peek() + maxPq.peek()) / 2.0;
        } else {
            return maxPq.peek();
        }
    }
}
