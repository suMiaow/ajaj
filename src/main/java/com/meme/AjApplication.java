package com.meme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;

@Slf4j
@SpringBootApplication
public class AjApplication {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(AjApplication.class);

        log.info(context.getMessage("hi", null, Locale.SIMPLIFIED_CHINESE));
        log.info(context.getMessage("hi", null, Locale.ENGLISH));
        log.info(context.getMessage("hi", null, Locale.JAPANESE));

        Resource[] resources = context.getResources("classpath:application.yml");
        Resource[] springFactories = context.getResources("classpath*:META-INF/spring.factories");

        log.info("{}", springFactories);

    }
}
