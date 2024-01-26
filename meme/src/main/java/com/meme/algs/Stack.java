package com.meme.algs;

public interface Stack<E> {

    boolean push(E item);

    E pop();
    E peek();

    boolean isEmpty();
    boolean isFull();


}
