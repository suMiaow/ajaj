package com.meme.beans.service.impl;

import com.meme.beans.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
//@Service("demo-service")
public class BbbDemoService implements DemoService {
    @Override
    public void fun() {
        log.info("from bbbDemoService");
    }
}
