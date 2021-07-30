package com.example.rabbitmq_demo.consumer;

import com.example.rabbitmq_demo.config.MessageConfig;
import com.example.rabbitmq_demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from queue"+ orderStatus);


    }
}
