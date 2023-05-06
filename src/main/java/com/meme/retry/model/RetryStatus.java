package com.meme.retry.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RetryStatus {

    /**
     * 失败
     */
    FAIL("0"),

    /**
     * 成功
     */
    SUCCESS("1"),

    /**
     * 结束
     */
    FINALIZED("3"),
    ;

    private final String code;

}
