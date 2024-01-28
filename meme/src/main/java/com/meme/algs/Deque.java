package com.meme.algs;

public interface Deque<E> {
    boolean offerFirst(E e);
    boolean offerLast(E e);
    E pollFirst();
    E pollLast();

    E peekFirst();
    E peekLast();

    boolean isEmpty();
    boolean isFull();



}
