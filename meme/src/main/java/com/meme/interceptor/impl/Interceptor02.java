package com.meme.interceptor.impl;

import com.meme.interceptor.MeInterceptor;
import com.meme.interceptor.extd.MeStrInterceptor;
import com.meme.interceptor.model.HandleResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Interceptor02 implements MeStrInterceptor {

    @Override
    public HandleResult<String> preHandle(String requestBody) {
        if (StringUtils.contains(requestBody, "02")) {
            return HandleResult.success(requestBody + "{02 intercepted}");
        } else {
            return HandleResult.failWithMessage(requestBody + "{02 intercepted}",  "fail 02");
        }
    }


}
