package com.meme.algs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedListSentinel implements Iterable<Integer> {
    private Node head = new Node(0, null); // 头指针 -> 哨兵结点

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            Node currentNode = head.next;

            @Override
            public boolean hasNext() {

                return currentNode != null;
            }

            @Override
            public Integer next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int result = currentNode.value;
                currentNode = currentNode.next;
                return result;
            }
        };
    }


    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    private Node findLast() {

        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();
        last.next = new Node(value, null);

    }

    public int get(int i) {

        Node found = findNode(i);
        if (found == null) {
            throw new IndexOutOfBoundsException();
        }
        return found.value;

    }

    private Node findNode(int i) {

        Node currentNode = head;
        for (int index = -1; currentNode != null; index++, currentNode = currentNode.next) {
            if (index == i) {
                return currentNode;
            }
        }
        return null;
    }

    public void insert(int index, int value) {

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node previous = findNode(index - 1);

        if (previous != null) {
            previous.next = new Node(value, previous.next);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void removeFirst() {
        remove(0);
    }

    public void remove(int index) {
        Node previous = findNode(index - 1);
        if (previous != null) {
            Node p = previous.next;
            if (p != null) {
                previous.next = p.next;
            }
        }
    }


    public static void main(String[] args) {
        SinglyLinkedListSentinel singlyLinkedList = new SinglyLinkedListSentinel();
        singlyLinkedList.addLast(1);
        singlyLinkedList.addLast(2);
        singlyLinkedList.addLast(3);
        singlyLinkedList.addLast(4);
        singlyLinkedList.addLast(5);
        singlyLinkedList.removeFirst();

        singlyLinkedList.forEach(System.out::println);

        System.out.println(singlyLinkedList.get(5));


    }
}
