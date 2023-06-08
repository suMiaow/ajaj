package com.meme.controller;

import com.meme.exception.DemoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/demo")
@RestController
@Slf4j
public class DemoController {

    @PostMapping("/test")
    public Object test(@RequestBody String aaa) {

//        return aaa + "1";

        throw new DemoException("exeexxxxxxxxxxxxxx");
    }

    @PostMapping("/path/{a}")
    public Object path(@PathVariable String a) {
        log.info("a: {}", a);
        return a;
    }

    @PostMapping("/path/aaa")
        public Object pathaaa(){
        log.info("nnnnnnn");
        return "nnnnn";
    }

}
