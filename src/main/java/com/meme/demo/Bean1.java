package com.meme.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean1 {

    @Autowired
    private Bean2 bean2;

    public String fun() {
        return bean2.fun();
    }
}
