package com.meme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Demo1Service {

    @Autowired
    DemoService demoService;

    public Integer fun1() {
        return demoService.fun();
    }
}
