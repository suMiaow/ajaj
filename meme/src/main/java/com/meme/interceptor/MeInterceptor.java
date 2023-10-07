package com.meme.interceptor;

import com.meme.interceptor.model.HandleResult;

public interface MeInterceptor<T, R> {

    default HandleResult<T> preHandle(T t) {
        return HandleResult.success(t);
    }

    default HandleResult<R> postHandle(R r) {
        return HandleResult.success(r);
    }
}
