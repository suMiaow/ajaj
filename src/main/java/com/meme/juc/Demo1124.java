package com.meme.juc;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Demo1124")
public class Demo1124 {

    public static void main(String[] args) {
        Person person1 = new Person("AAA", "bbb");
        Person person2 = new Person("AAA", "bbb");

        log.info("person1 equals: {}", person1.equals(person2));

    }
}
