package com.meme.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bean1 {

    private final Bean2 bean2;

    public Bean1(@Autowired(required = false) Bean2 bean2) {
        this.bean2 = bean2;
    }


    public String fun() {
        return bean2.fun();
    }

    @Autowired
    public void setValue(@Value("aaa:aaa") String aaa) {
        log.info("autowired setValue");
    }

}
