package com.meme.beans.service.config;

import com.meme.beans.client.impl.BbbDemoClient;
import com.meme.beans.service.DemoService;
import com.meme.beans.service.impl.AaaDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig2 {

//    @Bean("demonService")
//    public DemoService demoService( @Autowired(required = false) BbbDemoClient bbbDemoClient ) {
//        return new AaaDemoService(bbbDemoClient);
//    }

}
