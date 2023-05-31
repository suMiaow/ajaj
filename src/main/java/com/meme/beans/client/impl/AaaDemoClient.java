package com.meme.beans.client.impl;

import com.meme.beans.client.DemoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Primary
public class AaaDemoClient implements DemoClient {
    @Override
    public void fun() {
        log.info("from aaa demoClient");
    }
}
