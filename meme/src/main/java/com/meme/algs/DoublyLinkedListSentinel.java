package com.meme.algs;

import java.util.Iterator;

public class DoublyLinkedListSentinel implements Iterable<Integer> {

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

    private final Node head;
    private final Node tail;

    public DoublyLinkedListSentinel() {
        this.head = new Node(null, 0, null);
        this.tail = new Node(null, 0, null);
        this.head.next = tail;
        this.tail.prev = head;
    }

    private Node findNode(int index) {
        Node p = head;
        for (int i = -1; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    public void removeFirst() {
        remove(0);
    }

    public void addLast(int value) {
        Node prev = tail.prev;
        tail.prev = prev.next = new Node(prev, value, tail);
    }

    public void removeLast() {
        Node toRemove = tail.prev;
        if (toRemove == head) {
            throw new IndexOutOfBoundsException();
        }
        Node prev = toRemove.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        Node next = prev.next;
        Node toInsert = new Node(prev, value, next);
        next.prev = prev.next = toInsert;
    }

    public void remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node toRemove = findNode(index);
        Node prev = toRemove.prev;
        Node next = toRemove.next;
        prev.next = next;
        next.prev = prev;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
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

    public static void main(String[] args) {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();
        list.addFirst(8);
        list.insert(0, 10);
        list.insert(1, 11);
        list.insert(2, 12);
        list.addLast(13);
        list.addLast(14);
        list.addFirst(9);

        System.out.println(list);

        list.removeLast();
        System.out.println(list);

    }
}
