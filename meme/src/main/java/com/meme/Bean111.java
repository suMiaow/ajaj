package com.meme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Bean111 {

    @Autowired
    private Bean1 bean1;

    @Value("${aaa:aaa}")
    private String aaa;

    public void fun() {
        bean1.fun();
        System.out.println(aaa);
    }
}
