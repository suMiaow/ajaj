package com.meme.beans.service.impl;

import com.meme.beans.client.DemoClient;
import com.meme.beans.service.Consumer;
import com.meme.beans.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class AaaDemoService implements DemoService, Consumer {

    @Autowired
    private DemoClient demoClient;

    @Value("${spring.application.name:}")
    private String appName;

    @Override
    public void fun() {

        log.info("appName: {}", appName);

        demoClient.fun();
    }

    @Override
    public void consume() {

    }
}
