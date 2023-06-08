package com.meme.beans.service.config;

import com.meme.beans.client.DemoClient;
import com.meme.beans.client.impl.AaaDemoClient;
import com.meme.beans.client.impl.BbbDemoClient;
import com.meme.beans.client.impl.CccDemoClient;
import com.meme.beans.service.DemoService;
import com.meme.beans.service.impl.AaaDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ServiceConfig {


//    @ConditionalOnProperty(name = "demo.client.bean", havingValue = "aaa", matchIfMissing = true)
    @Bean
    @Primary
    public DemoService demoService0() {
        return new AaaDemoService();
    }

//    @ConditionalOnProperty(name = "demo.client.bean", havingValue = "bbb")
//    @Bean
//    @Primary
//    public DemoService demoService1( @Autowired(required = false) BbbDemoClient demoClient ) {
//        return new AaaDemoService(demoClient);
//    }
//
//    @Bean
//    @ConditionalOnProperty(name = "demo.client.bean", havingValue = "ccc")
//    @Primary
//    public DemoService demoService2( @Autowired(required = false) CccDemoClient demoClient ) {
//        return new AaaDemoService(demoClient);
//    }
//
//
//    @Bean("bbbDemoService")
//    public DemoService bbbDemoService( @Autowired(required = false) BbbDemoClient demoClient ) {
//        return new AaaDemoService(demoClient);
//    }
//
//    @Bean("cccDemoService")
//    public DemoService cccDemoService( @Autowired(required = false) CccDemoClient demoClient ) {
//        return new AaaDemoService(demoClient);
//    }
}
