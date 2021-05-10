package com.hmses.demo.configuration;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${simple.rabbitmq.exchange}")
    private String exchange;

    @Value("${send.rabbitmq.routingKey}")
    private String sendRoutingKey;

    @Value("${send.rabbitmq.queue}")
    private String sendQueue;

    @Value("${receive.rabbitmq.routingKey}")
    private String receiveRoutingKey;

    @Value("${receive.rabbitmq.queue}")
    private String receiveQueue;

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue sendQueue() {
        return new Queue(sendQueue, true);
    }

    @Bean
    Queue receiveQueue() {
        return new Queue(receiveQueue, true);
    }

    @Bean
    Binding sendBinding(Queue sendQueue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(sendQueue)
                .to(directExchange)
                .with(sendRoutingKey);
    }

    @Bean
    Binding receiveBinding(Queue receiveQueue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(receiveQueue)
                .to(directExchange)
                .with(receiveRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
