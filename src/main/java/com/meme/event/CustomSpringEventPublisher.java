package com.meme.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomSpringEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent() {
        log.info("Publishing custom event.");
        CustomSpringEvent customSpringEvent = new CustomSpringEvent(this, "hi from publisher");
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}