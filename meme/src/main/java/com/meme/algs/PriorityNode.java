package com.meme.algs;

public class PriorityNode implements Priority {
    String value;

    int priority;

    @Override
    public int priority() {
        return this.priority;
    }

    public PriorityNode(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "{'" + value + '\'' + ", " + priority +
                '}';
    }
}
