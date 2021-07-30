package com.example.rabbitmq_demo.publisher;

import com.example.rabbitmq_demo.config.MessageConfig;
import com.example.rabbitmq_demo.dto.Order;
import com.example.rabbitmq_demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired //You can use @Autowired annotation on properties to get rid of the setter methods
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS","order placed sucessfully in"+ restaurantName);
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY,orderStatus);
        return "Success";
    }

}
