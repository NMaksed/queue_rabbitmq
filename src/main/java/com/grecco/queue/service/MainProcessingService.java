package com.grecco.queue.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MainProcessingService {

    @RabbitListener(queues = "main_queue")
    public void process(String main) {
        System.out.println("Mensagem Recebida: " + main);
    }
}
