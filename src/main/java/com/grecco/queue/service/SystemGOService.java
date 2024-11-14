package com.grecco.queue.service;

import com.grecco.queue.consumer.EnfileiramentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemGOService {

    private final RabbitTemplate rabbitTemplate;
    private final EnfileiramentoService enfileiramentoService;

    @Autowired
    public SystemGOService(RabbitTemplate rabbitTemplate,
                           EnfileiramentoService enfileiramentoService) {
        this.rabbitTemplate = rabbitTemplate;
        this.enfileiramentoService = enfileiramentoService;
    }

    @RabbitListener(queues = "system_GO_queue")
    public void process(String systemGO) {
        //rabbitTemplate.convertAndSend("system_GO.queue", systemGO);
        enfileiramentoService.enfileiraGOAutbank(systemGO);
    }
}
