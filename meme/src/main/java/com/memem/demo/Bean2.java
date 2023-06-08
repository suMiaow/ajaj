package com.memem.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean2 {

    @Autowired
    private Bean3 bean3;

    public String fun() {
        return bean3.fun();
    }
}
