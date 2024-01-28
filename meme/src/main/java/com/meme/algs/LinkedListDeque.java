package com.meme.algs;

import com.meme.algs.common.AlgsUtil;

import java.util.Iterator;

public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;
    int size;
    Node<E> sentinel = new Node<>(null, null, null);
    Node<E> tail;



    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        tail = sentinel;
        tail.next = sentinel;
        tail.prev = sentinel;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()){
            return false;
        }

        sentinel.next = new Node<>(sentinel, e, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;

        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()){
            return false;
        }

        sentinel.prev = new Node<>(sentinel.prev, e, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;

        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> result = sentinel.next;
        sentinel.next = result.next;
        sentinel.next.prev = sentinel;
        size--;
        return result.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> result = sentinel.prev;
        sentinel.prev = result.prev;
        sentinel.prev.next = sentinel;
        size--;
        return result.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.value;
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

            Node<E> p = sentinel;

            @Override

            public boolean hasNext() {
                return p.next != sentinel;
            }

            @Override
            public E next() {
                return (p = p.next).value;
            }
        };
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>(10);
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
