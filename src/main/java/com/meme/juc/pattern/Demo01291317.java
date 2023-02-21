package com.meme.juc.pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo01291317 {

    static final Object lock = new Object();
    static boolean t2Runned = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!t2Runned) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
                log.debug("1");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                log.debug("2");
                t2Runned = true;
                lock.notifyAll();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
