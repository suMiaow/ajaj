package com.meme.interceptor.model;

import lombok.Getter;

@Getter
public class HandleResult<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

    private static final String CODE_SUCCESS = "success";
    private static final String CODE_FAIL = "fail";

    private HandleResult() {
    }

    public static <T> HandleResult<T> success(T t) {
        HandleResult<T> handleResult = new HandleResult<>();
        handleResult.code = CODE_SUCCESS;
        handleResult.success = true;
        handleResult.data = t;
        return handleResult;
    }

    public static <T> HandleResult<T> fail(T t, String code, String message) {
        HandleResult<T> handleResult = new HandleResult<>();
        handleResult.data = t;
        handleResult.code = code;
        handleResult.success = false;
        handleResult.message = message;
        return handleResult;
    }

    public static <T> HandleResult<T> fail(T t) {
        return fail(t, CODE_FAIL, CODE_FAIL);
    }


    public static <T> HandleResult<T> failWithMessage(T t, String message) {
        return fail(t, CODE_FAIL, message);
    }

    public static <T> HandleResult<T> failWithCode(T t, String code) {
        return fail(t, code, CODE_FAIL);
    }
}
