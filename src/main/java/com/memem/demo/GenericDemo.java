package com.memem.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public class GenericDemo {
    public static void main(String[] args) {
        GenericService aaaaServcice = new AaaaServcice();
        Class<? extends GenericService> clazz = aaaaServcice.getClass();
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {

            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericInterface;
                log.info("check type: {}", pt.getRawType().equals(GenericService.class));
                log.info("check String: {}", pt.getActualTypeArguments()[0].equals(String.class));
            }
        }
        Type genericSuperclass = clazz.getGenericSuperclass();

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericSuperclass;
            for (Type actualTypeArgument : pt.getActualTypeArguments()) {
                log.info("{}", actualTypeArgument);
            }
        }
    }

    public interface GenericService<T, R> {
        R foo(T t);
    }

    public static class AaaaServcice implements GenericService<String, Integer> {

        @Override
        public Integer foo(String s) {
            return Integer.parseInt(s);
        }
    }
}
