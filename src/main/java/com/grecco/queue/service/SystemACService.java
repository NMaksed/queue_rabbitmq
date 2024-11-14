package com.grecco.queue.service;

import com.grecco.queue.consumer.EnfileiramentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemACService {

    private final RabbitTemplate rabbitTemplate;
    private final EnfileiramentoService enfileiramentoService;

    @Autowired
    public SystemACService(RabbitTemplate rabbitTemplate,
                           EnfileiramentoService enfileiramentoService) {
        this.rabbitTemplate = rabbitTemplate;
        this.enfileiramentoService = enfileiramentoService;
    }

    @RabbitListener(queues = "system_AC_queue")
    public void processSystemAC(String systemAMessage) {
        //rabbitTemplate.convertAndSend("system_AC.queue", systemAMessage);
        enfileiramentoService.enfileiraACAutbank(systemAMessage);
    }
}
