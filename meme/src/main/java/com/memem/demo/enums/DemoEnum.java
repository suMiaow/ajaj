package com.memem.demo.enums;

import com.alibaba.fastjson.JSON;
import com.memem.demo.model.DemoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
@Slf4j
public enum DemoEnum {

    A("a", "aaa"),
    B("b", "bbb"),
    ;

    private final String code;

    private final String value;

    public static void main(String[] args) {
        DemoModel demoModel = new DemoModel();
        demoModel.setCode("111111");
        demoModel.setDemoEnum(DemoEnum.B);
        String jsonStr = JSON.toJSONString(demoModel);
        log.info(jsonStr);
        JSON.parseObject(jsonStr, DemoModel.class);

        log.info("demoModel: {}", demoModel);
    }
}
