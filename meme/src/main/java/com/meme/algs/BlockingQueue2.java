package com.meme.algs;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue2<E> implements BlockingQueue<E> {


    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();

    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();


    public BlockingQueue2(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {

        tailLock.lockInterruptibly();
        int c;
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            c = size.incrementAndGet();
            if (c < size.get()) {
                tailWaits.signal();
            }
        } finally {
            tailLock.unlock();
        }
        if (c == 1) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E result;
        headLock.lockInterruptibly();
        int c;
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            result = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            c = size.getAndDecrement();
            if (c > 1) {
                headWaits.signal();
            }
        } finally {
            headLock.unlock();
        }
        if (c == size.get()) {
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }
        return result;
    }
}
