package com.meme.algs;

import java.util.concurrent.TimeUnit;

public interface BlockingQueue<E> {

    void offer(E e) throws InterruptedException;
    boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;
    E poll() throws InterruptedException;
}
