package com.meme.algs;

import java.util.Iterator;

public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    private int capacity;
    private int size = 0;

    private Node<E> head = new Node<>();

    private class Node<E> {
        E val;

        Node<E> next;

        Node() {}

        Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean push(E item) {
        if (isFull()) {
            return false;
        }
        head = new Node<>(item, head);
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E result = head.val;
        head = head.next;
        size--;
        return result;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.val;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean isFull() {
        return size >= capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> p = head;

            @Override
            public boolean hasNext() {
                return p.next != null;
            }

            @Override
            public E next() {
                E result = p.val;
                p = p.next;
                return result;
            }
        };
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> s = new LinkedListStack<>(10);
        for (int i = 0; i < 20; i++) {
            System.out.println("push: " + i + "\t" +s.push(i) );
        }
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
