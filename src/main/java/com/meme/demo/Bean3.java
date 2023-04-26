package com.meme.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean3 {

    @Autowired
    private Bean4 bean4;

    public String fun() {
        return bean4.fun();
    }
}
