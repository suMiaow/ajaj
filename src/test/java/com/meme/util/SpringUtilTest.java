package com.meme.util;

import com.meme.Demo1Service;
import com.meme.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@Slf4j
@SpringBootTest
public class SpringUtilTest {
/*
    @Test
    public void testBean() {
        System.out.println(

        SpringUtil.getBean("CommonService").getClass().getName()
        );
    }
*/
    @Mock
    DemoService demoServiceMock;
    @Autowired
    Demo1Service demo1Service;

    @Test
    void testDemo1() {
        System.out.println(demo1Service.fun1());
        System.out.println(demoServiceMock.fun());
        Mockito.doReturn(2).when(demoServiceMock).fun();
        ReflectionTestUtils.setField(demo1Service, "demoService", demoServiceMock);
        System.out.println(demo1Service.fun1());
    }

    @Test
    void testDemo2() {
        System.out.println(demo1Service.fun1());
    }

}
