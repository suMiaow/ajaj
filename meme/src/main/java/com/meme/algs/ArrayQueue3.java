package com.meme.algs;

import java.util.Iterator;

public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    private final E[] array;

    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {
        array = (E[]) new Object[1 << (int) Math.ceil(Math.log(capacity) / Math.log(2))];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail & (array.length - 1)] = value;
        tail++;

        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = array[head & (array.length - 1)];
        head++;

        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head & (array.length - 1)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int i = head;

            @Override
            public boolean hasNext() {
                return i < tail;
            }

            @Override
            public E next() {
                return array[i++ & (array.length - 1)];
            }
        };
    }

    public static void main(String[] args) {

        ArrayQueue3<Integer> queue = new ArrayQueue3<>(10);

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

    private static <E> void print(ArrayQueue3<E> queue) {

        queue.forEach(e -> System.out.print(e + "\t"));
        System.out.println();
    }
}
