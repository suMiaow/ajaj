package com.meme.algs;

import com.meme.algs.common.AlgsUtil;

import java.util.Iterator;

public class PriorityQueue2<E extends Priority> implements Queue<E>, Iterable<E> {

    private E[] array;
    private int size;

    public PriorityQueue2(int capacity) {
        array = (E[]) new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }

        int priority = value.priority();
        int i;
        for (i = size; i > 0; i--) {
            if (priority >= array[i - 1].priority()) {
                break;
            }
        }
        insert(i, value);

        size++;
        return true;
    }

    private void insert(int i, E value) {
        System.arraycopy(array, i, array, i + 1, size - i);
        array[i] = value;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = array[size - 1];
        array[--size] = null;
        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean isFull() {
        return size >= array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int p = 0;

            @Override
            public boolean hasNext() {
                return p < size;
            }

            @Override
            public E next() {
                return array[p++];
            }
        };
    }

    public static void main(String[] args) {
        PriorityQueue2<Priority> queue = new PriorityQueue2<>(10);
        queue.offer(new PriorityNode("a", 8));
        queue.offer(new PriorityNode("b", 4));
        queue.offer(new PriorityNode("c", 9));
        queue.offer(new PriorityNode("d", 2));
        queue.offer(new PriorityNode("e", 1));
        queue.offer(new PriorityNode("f", 8));
        queue.offer(new PriorityNode("g", 2));
        queue.offer(new PriorityNode("h", 10));
        queue.offer(new PriorityNode("i", 3));
        queue.offer(new PriorityNode("j", 0));
        queue.offer(new PriorityNode("k", 4));
        queue.offer(new PriorityNode("l", 38));

        System.out.println(AlgsUtil.iterableToString(queue));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
