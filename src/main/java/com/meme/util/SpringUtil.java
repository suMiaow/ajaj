package com.meme.util;

import com.meme.temp.handler.ServiceHandler;
import com.meme.temp.handler.model.request.DemoRequest;
import com.meme.temp.handler.model.response.DemoResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext; // NOSONAR
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static Object getBean(String name, ParameterizedTypeReference<?> typeReference) {
        ResolvableType resolvableType = ResolvableType.forType(new ParameterizedTypeReference<ServiceHandler<DemoRequest, DemoResponse>>() {
        });
        String[] beanNamesForType = applicationContext.getBeanNamesForType(resolvableType);
        return applicationContext.getBean(beanNamesForType[0]);
    }
}
