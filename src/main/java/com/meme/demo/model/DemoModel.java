package com.meme.demo.model;

import com.meme.demo.enums.DemoEnum;
import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Condition;

@Data
public class DemoModel {

    private String code;

    private DemoEnum demoEnum;

    public static void main(String[] args) {
//        Condition
    }
}
