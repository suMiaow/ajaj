package com.meme.demo.model;

import com.meme.demo.enums.DemoEnum;
import lombok.Data;

@Data
public class DemoModel {

    private String code;

    private DemoEnum demoEnum;
}
