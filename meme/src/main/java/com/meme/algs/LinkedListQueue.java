package com.meme.algs;

import java.util.Iterator;

public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {

    private class Node {
        E e;
        Node next;

        Node() {
        }

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private final Node sentinel = new Node();
    private Node tail = sentinel;

    public LinkedListQueue() {
        sentinel.next = sentinel;
    }

    @Override
    public boolean offer(E value) {
        tail = tail.next = new Node(value, sentinel);
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E result = sentinel.next.e;
        sentinel.next = sentinel.next.next;
        if (sentinel.next == sentinel) {
            tail = sentinel;
        }
        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.e;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node current = sentinel;

            @Override
            public boolean hasNext() {
                return current.next != sentinel;
            }

            @Override
            public E next() {
                current = current.next;
                return current.e;
            }
        };
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
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

    private static <E> void print(LinkedListQueue<E> queue) {

        queue.forEach(e -> {
            System.out.print(e + "\t");
        });
        System.out.println();
    }
}
