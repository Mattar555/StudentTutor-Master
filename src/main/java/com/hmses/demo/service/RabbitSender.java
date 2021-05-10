package com.hmses.demo.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${simple.rabbitmq.exchange}")
    private String exchange;

    @Value("${send.rabbitmq.routingKey}")
    private String routingKey;

    public void send(String emailAddress) {
        rabbitTemplate.convertAndSend(exchange, routingKey, emailAddress);
    }
}
