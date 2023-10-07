package com.meme.algs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class SinglyLinkedList implements Iterable<Integer> {
    private Node head;

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            Node currentNode = head;

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
        head = new Node(value, head);
    }

    private Node findLast() {
        if (head == null) {
            return null;
        }

        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;

    }

    public void addLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
        } else {
            last.next = new Node(value, null);
        }

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
        for (int index = 0; currentNode != null; index++, currentNode = currentNode.next) {
            if (index == i) {
                return currentNode;
            }
        }
        return null;
    }

    private void insert(int index, int value) {

        if (index <= 0) {
            addFirst(value);
        } else {

            Node previous = findNode(index - 1);

            if (previous != null) {
                previous.next = new Node(value, previous.next);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    public void remove(int index) {
        if (index == 0) {
            removeFirst();
        }
        Node previous = findNode(index - 1);
        if (previous != null) {
            Node p = previous.next;
            if (p != null) {
                previous.next = p.next;
            }
        }
    }


    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.addLast(1);
        singlyLinkedList.addLast(2);
        singlyLinkedList.addLast(3);
        singlyLinkedList.addLast(4);
        singlyLinkedList.addLast(5);
        singlyLinkedList.remove(0);

        singlyLinkedList.forEach(System.out::println);



    }
}
