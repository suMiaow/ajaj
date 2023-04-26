package com.meme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AjApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AjApplication.class);


        System.out.println(context);

    }
}
