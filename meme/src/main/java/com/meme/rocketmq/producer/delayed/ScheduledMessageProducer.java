package com.meme.rocketmq.producer.delayed;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class ScheduledMessageProducer {
    public static void main(String[] args) throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer("TestProducerGroup");
        producer.setNamesrvAddr("172.19.109.56:9876");
        // Launch producer
        producer.start();
        int totalMessagesToSend = 1;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic20230323", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
            message.setDelayTimeLevel(4);
            // Send the message
            producer.send(message);
        }

        // Shutdown producer after use.
        producer.shutdown();
    }

}