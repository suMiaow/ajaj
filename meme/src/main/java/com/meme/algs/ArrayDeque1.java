package com.meme.algs;

import com.meme.algs.common.AlgsUtil;

import java.util.Iterator;

public class ArrayDeque1<E> implements Deque<E>, Iterable<E> {

    private E[] array;
    int head;
    int tail;

    @SuppressWarnings("all")
    public ArrayDeque1(int capacity) {
        this.array = (E[]) new Object[capacity + 1];
    }

    static int inc(int i, int length) {
        if (i + 1 >= length) {
            return 0;
        }
        return i + 1;
    }
    static int dec(int i, int length) {
        if (i < 1) {
            return length - 1;
        }
        return i - 1;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        head = dec(head, array.length);
        array[head] = e;
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        array[tail] = e;
        tail = inc(tail, array.length);
        return false;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E result = array[head];
        array[head] = null;
        head = inc(head, array.length);
        return result;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        tail = dec(tail, array.length);
        E result = array[tail];
        array[tail] = null;
        return result;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[dec(tail, array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return inc(tail, array.length) == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E result = array[p];
                p = inc(p, array.length);
                return result;
            }
        };
    }

    public static void main(String[] args) {

        ArrayDeque1<Integer> deque = new ArrayDeque1<>(2);
        System.out.println(deque.offerFirst(1)); // 1
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.offerFirst(2)); // 2 1
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.offerFirst(3)); // 3 2 1
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.pollLast()); // 3 2
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.pollFirst()); // 2
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.offerLast(4)); // 2 4
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.offerFirst(5)); // 5 2 4
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.pollLast()); // 5 2
        System.out.println(AlgsUtil.iterableToString(deque));
        System.out.println(deque.pollFirst()); // 2
        System.out.println(AlgsUtil.iterableToString(deque));
    }
}
