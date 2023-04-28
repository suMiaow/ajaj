package com.meme.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ContextRefreshedListener {

    @Async
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent cse) {
        log.info("Handling context refreshed event.");
    }
}