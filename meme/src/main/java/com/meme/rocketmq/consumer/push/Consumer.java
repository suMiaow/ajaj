package com.meme.rocketmq.consumer.push;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        // 初始化consumer，并设置consumer group name
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_group_name");

        // 设置NameServer地址
        consumer.setNamesrvAddr("172.19.109.56:9876");
        //订阅一个或多个topic，并指定tag过滤条件，这里指定*表示接收所有tag的消息
        consumer.subscribe("TestTopic20230323", "*");
        //注册回调接口来处理从Broker中收到的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                if (CollectionUtils.isNotEmpty(msgs)) {
                    for (MessageExt msg : msgs) {
                        log.info("-----------------------------------------------------------------------------------");
                        log.info("msgId: {}", msg.getMsgId());
                        log.info("body: {}", new String(msg.getBody(), StandardCharsets.UTF_8));
                        log.info("reconsumeTimes: {}", msg.getReconsumeTimes());
                        log.info("bornTimestamp: {}", msg.getBornTimestamp());
                    }
                }
                // 返回消息消费状态，ConsumeConcurrentlyStatus.CONSUME_SUCCESS为消费成功
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        // 启动Consumer
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}