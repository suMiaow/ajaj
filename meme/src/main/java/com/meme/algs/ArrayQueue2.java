package com.meme.algs;

import java.util.Iterator;

public class ArrayQueue2<E> implements Queue<E>, Iterable<E> {

    private E[] array;

    private int head = 0;
    private int tail = 0;
    private int size = 0;

    @SuppressWarnings("all")
    ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % (array.length);
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = array[head];
        head = (head + 1) % (array.length);
        size--;
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
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int i = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E result = array[i];
                i = (i+1) % array.length;
                count++;
                return result;
            }
        };
    }


    public static void main(String[] args) {
        ArrayQueue2<Integer> queue = new ArrayQueue2<>(10);

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

    private static <E> void print(ArrayQueue2<E> queue) {

        queue.forEach(e -> {
            System.out.print(e + "\t");
        });
        System.out.println();
    }
}
