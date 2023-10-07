package com.meme.interceptor.impl;

import com.meme.interceptor.MeInterceptor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class Interceptor03 implements MeInterceptor<Integer, String> {

}
