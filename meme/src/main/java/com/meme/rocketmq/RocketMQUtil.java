package com.meme.rocketmq;

import lombok.experimental.UtilityClass;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

@UtilityClass
public class RocketMQUtil {

    public static DefaultMQProducer getDefaultProducer(String producerGroup, String namesrvAddr) {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        return producer;
    }
}
