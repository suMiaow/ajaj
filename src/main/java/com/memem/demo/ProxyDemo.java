package com.memem.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

@Slf4j
public class ProxyDemo {

    public interface AaaInterface {
        void foo();

        void bar();
    }

    public static void main(String[] args) {

        AaaInterface tempAaa = new AaaInterface() {
            @Override
            public void foo() {
                log.info("foo");
            }

            @Override
            public void bar() {
                log.info("bar");
            }
        };

        AaaInterface proxiedAaa = (AaaInterface) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[]{AaaInterface.class}, (proxy, method, args1) -> {
            if ("foo".equals(method.getName())) {
                log.info("before foo");
                Object result = method.invoke(tempAaa, args1);
                log.info("after foo");
                return result;
            } else if ("bar".equals(method.getName())) {
                log.info("before bar");
                Object result = method.invoke(tempAaa, args1);
                log.info("after bar");
                return result;
            } else {
                return method.invoke(tempAaa, args1);
            }
        });

        proxiedAaa.foo();
        proxiedAaa.bar();

    }
}
