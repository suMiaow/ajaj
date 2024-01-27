package com.meme.algs;

import com.meme.algs.common.AlgsUtil;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    private E[] array;

    private int top = -1;

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E item) {
        if (isFull()) {
            return false;
        }
        array[++top] = item;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }

        return array[top--];
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == array.length - 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int p = 0;

            @Override
            public boolean hasNext() {
                return p <= top;
            }

            @Override
            public E next() {
                return array[p++];
            }
        };
    }

    public static void main(String[] args) {

        ArrayStack<Integer> s = new ArrayStack<>(10);
        for (int i = 0; i < 20; i++) {
            System.out.println("push: " + i + "\t" +s.push(i) );
        }
        System.out.println(AlgsUtil.iterableToString(s));
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());
        System.out.println("pop: " + s.pop());

    }
}
