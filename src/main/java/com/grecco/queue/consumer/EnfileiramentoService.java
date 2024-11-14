package com.grecco.queue.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnfileiramentoService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EnfileiramentoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "system_AC_queue")
    public void enfileiraACAutbank(String object) {
        rabbitTemplate.convertAndSend("exchange", "main.queue", object);
    }

    @RabbitListener(queues = "system_GO_queue")
    public void enfileiraGOAutbank(String object) {
        rabbitTemplate.convertAndSend("exchange", "main.queue", object);
    }
}
