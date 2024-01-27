package com.meme.algs.leetcode;

import java.util.Stack;

public class E232 {

    private Stack<Integer> headStack = new Stack<>();
    private Stack<Integer> tailStack = new Stack<>();

    public E232() {
    }

    public void push(int x) {

        tailStack.push(x);
    }

    public int pop() {
        transfer();
        return headStack.pop();
    }

    public int peek() {

        transfer();
        return headStack.peek();
    }

    public boolean empty() {
        return headStack.empty() && tailStack.empty();
    }

    private void transfer() {
        if (headStack.empty()) {
            while (!tailStack.empty()) {
                headStack.push(tailStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        E232 myQueue = new E232();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(
                myQueue.peek() // return 1
        );
        System.out.println(
                myQueue.pop() // return 1, queue is [2]
        );
        System.out.println(
                myQueue.empty() // return false
        );
    }
}
