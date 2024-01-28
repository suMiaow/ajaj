package com.meme.algs.leetcode;

import java.util.LinkedList;

public class E225 {


    public E225() {

    }

    private LinkedList<Integer> queue = new LinkedList<>();
    public void push(int x) {
        queue.push(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.push(queue.pop());
        }
    }

    public int pop() {
        return queue.pop();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
