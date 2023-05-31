package com.meme.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
public class BeanFactoryDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition configBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(BeanConfig.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", configBeanDefinition);


        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessors = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessors.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
        Map<String, BeanPostProcessor> beanPostProcessors = beanFactory.getBeansOfType(BeanPostProcessor.class);
        beanFactory.addBeanPostProcessors(beanPostProcessors.values());

        beanFactory.preInstantiateSingletons();

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            log.info(beanDefinitionName);
        }

        log.info("bean1.bean2: {}", ((Bean1) beanFactory.getBean("bean1")).bean2);

        DefaultListableBeanFactory beanFactory1 = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory1);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("bean_def.xml"));

    }

    @Configuration
    public static class BeanConfig {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

    }

    public static class Bean1 {

        public Bean1() {
            log.info("construct bean1");
        }

        @Autowired
        private Bean2 bean2;

    }


    public static class Bean2 {

        public Bean2() {
            log.info("construct bean2");
        }
    }
}
