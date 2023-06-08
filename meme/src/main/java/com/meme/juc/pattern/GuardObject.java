package com.meme.juc.pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Guarded suspension")
public class GuardObject {

    private Object response;

    public Object get(long timeout) {
        synchronized (this) {
            long beginTime = System.currentTimeMillis();
            long passedTime = 0;
            while (response == null) {
                long waitTime = timeout - passedTime;
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                    Thread.currentThread().interrupt();
                }

                passedTime = System.currentTimeMillis() - beginTime;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}
