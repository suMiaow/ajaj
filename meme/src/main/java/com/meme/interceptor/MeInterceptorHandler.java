package com.meme.interceptor;

import com.meme.interceptor.model.HandleResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@Slf4j
public class MeInterceptorHandler<T, R> {

    private final List<? extends MeInterceptor<T, R>> meInterceptorList;

    public MeInterceptorHandler(List<? extends MeInterceptor<T, R>> meInterceptorList) {
        this.meInterceptorList = meInterceptorList;
    }

    public HandleResult<T> preHandle(T t) {

        T handledPram = t;
        HandleResult<T> handleResult = null;

        if (CollectionUtils.isNotEmpty(meInterceptorList)) {
            for (MeInterceptor<T, R> interceptor : meInterceptorList) {
                try {
                    handleResult = interceptor.preHandle(handledPram);
                    if (handleResult.isSuccess()) {
                        handledPram = handleResult.getData();
                    } else {
                        return handleResult;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    return HandleResult.failWithMessage(handledPram, e.getMessage());
                }
            }
        }

        return handleResult == null ? HandleResult.success(handledPram) : handleResult;
    }

    public HandleResult<R> postHandle(R r) {


        R handledPram = r;
        HandleResult<R> handleResult = null;

        if (CollectionUtils.isNotEmpty(meInterceptorList)) {
            for (MeInterceptor<T, R> interceptor : meInterceptorList) {
                try {
                    handleResult = interceptor.postHandle(handledPram);
                    if (handleResult.isSuccess()) {
                        handledPram = handleResult.getData();
                    } else {
                        return handleResult;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    return HandleResult.failWithMessage(handledPram, e.getMessage());
                }
            }
        }

        return handleResult == null ? HandleResult.success(handledPram) : handleResult;
    }

}
