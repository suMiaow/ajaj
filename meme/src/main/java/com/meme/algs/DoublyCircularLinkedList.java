package com.meme.algs;

import java.util.Iterator;

public class DoublyCircularLinkedList implements Iterable<Integer>{

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            Node p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int result = p.value;
                p = p.next;
                return result;
            }
        };
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        this.forEach(e -> sb.append(e).append(" "));

        sb.append("]");
        return sb.toString();
    }

    static class Node {

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        Node prev;
        int value;
        Node next;
    }

    private final Node sentinel = new Node(null, 0, null);

    public DoublyCircularLinkedList() {
        sentinel.prev = sentinel.next = sentinel;
    }

    public void addFirst(int value) {
        Node next = sentinel.next;
        sentinel.next = next.prev = new Node(sentinel, value, next);
    }

    public void addLast(int value) {
        Node prev = sentinel.prev;
        sentinel.prev = prev.next = new Node(prev, value, sentinel);
    }

    public void removeFirst() {
        Node toRemove = sentinel.next;
        if (toRemove == sentinel) {
            throw new IndexOutOfBoundsException();
        }
        Node next = toRemove.next;
        sentinel.next = next;
        next.prev = sentinel;
    }

    public void removeLast() {
        Node toRemove = sentinel.prev;
        if (toRemove == sentinel) {
            throw new IndexOutOfBoundsException();
        }
        Node prev = toRemove.prev;
        prev.next = sentinel;
        sentinel.prev = prev;
    }

    public void removeByValue(int value) {
        Node toRemove = findByValue(value);
        if (toRemove != null) {
            Node prev = toRemove.prev;
            Node next = toRemove.next;
            prev.next = next;
            next.prev = prev;
        }
    }

    private Node findByValue(int value) {
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            if (p.value == value) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.removeByValue(0);
        System.out.println(list);
    }
}
