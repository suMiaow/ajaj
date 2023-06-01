package com.meme.juc;

import java.util.concurrent.locks.LockSupport;

public class Demo0108 {

    public static void main(String[] args) {
        LockSupport.park();
    }
}
