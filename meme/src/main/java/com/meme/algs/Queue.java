package com.meme.algs;

public interface Queue<E> {

    boolean offer(E value);

    E poll();

    E peek();

    boolean isEmpty();

}
