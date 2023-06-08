package com.meme.beans.client.impl;

import com.meme.beans.client.DemoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CccDemoClient implements DemoClient {
    @Override
    public void fun() {
        log.info("from ccc demoClient");
    }
}
