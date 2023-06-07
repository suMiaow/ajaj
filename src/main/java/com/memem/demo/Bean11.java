package com.memem.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


@Slf4j
public class Bean11 {

    @Autowired
    public void setAaa(@Value("${aaa:aaa}") String aaa) {
        log.info(">>>>>>>>>>> autowire");
    }

    @Resource
    public void b(@Value("${bbb:bbb}") String bbb) {
        log.info(">>>>>>>>>>> resource");
    }

    @PostConstruct
    public void postConstruct() {
        log.info(">>>>>>>>>>> postConstruct");
    }

    @PreDestroy
    public void preDestory() {
        log.info(">>>>>>>>>>> preDestory");
    }
}
