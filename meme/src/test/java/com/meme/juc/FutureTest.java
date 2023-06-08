package com.meme.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTest {

    public void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        Future<String> future = executors.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "I'm ok";
        });
        log.info("isDone: {}", future.isDone());
        log.info("get: {}", future.get());
    }
}
