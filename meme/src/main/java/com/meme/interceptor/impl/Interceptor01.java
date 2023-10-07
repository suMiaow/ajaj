package com.meme.interceptor.impl;

import com.meme.interceptor.MeInterceptor;
import com.meme.interceptor.extd.MeStrInterceptor;
import com.meme.interceptor.model.HandleResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Interceptor01 implements MeStrInterceptor {

    @Override
    public HandleResult<String> preHandle(String requestBody) {
        if (StringUtils.contains(requestBody, "01")) {
            return HandleResult.success(requestBody + "{01 intercepted}");
        } else {
            return HandleResult.failWithMessage(requestBody + "{01 intercepted}", "fail 01");
        }
    }


}
