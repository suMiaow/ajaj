package com.meme.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo1127 {

    private static void sleep1() {

        Thread t1 = new Thread("t1") {

            @Override
            public void run() {
                log.info("state1: {}", Thread.currentThread().getState());
                try {
                    log.info("running...");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.info("state2: {}", Thread.currentThread().getState());
                    log.info("interrupted...");
                }
                log.info("running...");
            }

        };
        t1.start();
        log.info("state3: {}", t1.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.interrupt();
        log.info("state4: {}", t1.getState());
    }

    private static int r = 0;

    private static void join1() {

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    r = 10;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t1.start();
        try {
            t1.join(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("r: {}", r);

    }

    private static void interrupt1() {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {

                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        log.info("interrupted: {}", t1.isInterrupted());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        log.info("interrupted: {}", t1.isInterrupted());


    }

    private static void interrupt2() {

        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        }, "t1");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t1.interrupt();

    }

    private static void daemon1() {
        Thread t1 = new Thread(() -> {
            while (true) {


                log.info("running...");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {

            t1.setDaemon(true);
            t1.start();

        }, "t2");

        try {
            t2.start();
            TimeUnit.SECONDS.sleep(1);
            log.info("t1 state: {}", t1.getState());
            log.info("t2 state: {}", t2.getState());
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

//        sleep1();
//        join1();
//        interrupt1();
//        interrupt2();
        daemon1();


    }


}
