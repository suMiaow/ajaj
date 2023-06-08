package com.memem.demo.enums;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RequestModeEnum {

    RAW_JSON("raw_json"),
    FORM_DATA("formdata"),
    ;

    @Getter
    private final String code;

    RequestModeEnum(String code) {
        this.code = code;
    }

    public static RequestModeEnum getByCode(String code) {
        List<RequestModeEnum> enumFound = Arrays.stream(RequestModeEnum.values())
                .filter(requestModeEnum -> StringUtils.equalsIgnoreCase(requestModeEnum.getCode(), code))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(enumFound)) {
            return enumFound.get(0);
        } else {
            return null;
        }
    }
}
