package com.meme.algs;

import org.checkerframework.checker.units.qual.A;

import java.util.Iterator;

public class ArrayQueue1<E> implements Queue<E>, Iterable<E> {

    private E[] array;

    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % (array.length);
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = array[head];
        head = (head + 1) % (array.length);
        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % (array.length) == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int i = head;

            @Override
            public boolean hasNext() {
                return i != tail;
            }

            @Override
            public E next() {
                E result = array[i];
                i = (i+1) % array.length;
                return result;
            }
        };
    }


    public static void main(String[] args) {
        ArrayQueue1<Integer> queue = new ArrayQueue1<>(10);
        queue.offer(1);

        print(queue);
        queue.offer(2);
        print(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        queue.offer(4);
        print(queue);
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
        print(queue);
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
        System.out.println(queue.poll());
        print(queue);
    }

    private static <E> void print(ArrayQueue1<E> queue) {

        queue.forEach(e -> {
            System.out.print(e + "\t");
        });
        System.out.println();
    }
}
