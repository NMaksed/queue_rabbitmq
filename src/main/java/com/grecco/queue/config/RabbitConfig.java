package com.grecco.queue.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    //Fila principal
    @Bean
    public Queue mainQueue() {
        return new Queue("main_queue", true);
    }

    @Bean
    public Queue systemACqueue() {
        return new Queue("system_AC_queue", true);
    }

    @Bean
    public Queue systemGOQueue() {
        return new Queue("system_GO_queue", true);
    }

    @Bean
    public Binding bindingSystemAC(Queue mainQueue, TopicExchange exchange) {
        return BindingBuilder.bind(mainQueue).to(exchange).with("system_AC.*");
    }

    @Bean
    public Binding bindingSystemGO(Queue mainQueue, TopicExchange exchange) {
        return BindingBuilder.bind(mainQueue).to(exchange).with("system_GO.*");
    }

    @Bean
    public Binding bindingMain(Queue mainQueue, TopicExchange exchange) {
        return BindingBuilder.bind(mainQueue).to(exchange).with("main.*");
    }
}
