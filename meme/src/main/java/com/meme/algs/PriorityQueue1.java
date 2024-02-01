package com.meme.algs;

import com.meme.algs.common.AlgsUtil;

import java.util.Iterator;

public class PriorityQueue1<E extends Priority> implements Queue<E>, Iterable<E> {

    private E[] array;
    private int size;

    public PriorityQueue1(int capacity) {
        array = (E[]) new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int maxPriorityIndex = selectMaxPriorityIndex();
        E result = array[maxPriorityIndex];
        System.arraycopy(array, maxPriorityIndex + 1, array, maxPriorityIndex, array.length - maxPriorityIndex - 1);
        array[--size] = null;
        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int maxPriorityIndex = selectMaxPriorityIndex();
        return array[maxPriorityIndex];
    }

    private int selectMaxPriorityIndex() {

        int mi = 0;
        int mp = array[0].priority();

        for (int i = 1; i < size; i++) {
            int ip = array[i].priority();
            if (ip > mp) {
                mi = i;
                mp = ip;
            }
        }
        return mi;
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
        PriorityQueue1<Priority> queue = new PriorityQueue1<>(10);
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
