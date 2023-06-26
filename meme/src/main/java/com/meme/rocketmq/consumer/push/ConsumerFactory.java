package com.meme.rocketmq.consumer.push;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ConsumerFactory {

    public static DefaultMQPushConsumer getMqPushConsumer(String consumerGroup,
                                                    String namesrvAddr,
                                                    String topic,
                                                    int consumeThreadMin,
                                                    int consumeThreadMax,
                                                    MessageListenerConcurrently listener
                                                    ) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.subscribe(topic, "*");
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(listener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        return consumer;
    }

    public static void main(String[] args) throws MQClientException {

        getMqPushConsumer(
                "adapter_retry_consumer",
                "172.19.109.56:9876",
                "adapter_retry_topic_channel-pinduoduo",
                20,
                50,
                (msgs, context) -> {

                    int delayLevelNext = 1;
                    if (CollectionUtils.isNotEmpty(msgs)) {
                        for (MessageExt msg : msgs) {
                            log.info(new String(msg.getBody(), StandardCharsets.UTF_8));
                            delayLevelNext = msg.getReconsumeTimes() + 1;
                        }
                    }

                    context.setDelayLevelWhenNextConsume(delayLevelNext);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
        ).start();
    }
}
