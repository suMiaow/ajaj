package com.meme.algs;

import com.meme.algs.common.AlgsUtil;

import java.util.Iterator;

public class PriorityQueue4<E extends Priority> implements Queue<E>, Iterable<E> {

    private E[] array;
    private int size;

    public PriorityQueue4(int capacity) {
        array = (E[]) new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }

        int child = size++;
        for (int parent = (child - 1) / 2;
             child > 0 && array[parent].priority() < value.priority();
             child = parent, parent = (child - 1) / 2) {
            array[child] = array[parent];
        }

        array[child] = value;

        return true;
    }


    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = array[0];

        E tail = array[--size];
        int targetPriority = tail.priority();
        array[size] = null;
        int parent = 0;
        for (int leftChild = 1, rightChild = 2; leftChild < size; leftChild = parent * 2 + 1, rightChild = parent * 2 + 2) {
            int max = parent;
            int maxPriority = targetPriority;
            if (maxPriority < array[leftChild].priority()) {
                max = leftChild;
                maxPriority = array[leftChild].priority();
            }
            if (rightChild < size && maxPriority < array[rightChild].priority()) {
                max = rightChild;
            }
            if (parent == max) {
                break;
            } else {
                array[parent] = array[max];
                parent = max;
            }
        }
        array[parent] = tail;

        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return array[0];
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

        PriorityQueue4<Priority> queue = new PriorityQueue4<>(10);
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
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));
        System.out.println(queue.poll());
        System.out.println(AlgsUtil.iterableToString(queue));



    }
}
