package com.grecco.queue;

import com.grecco.queue.service.MainProcessingService;
import com.grecco.queue.service.SystemACService;
import com.grecco.queue.service.SystemGOService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitQueueApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RabbitTemplate rabbit, SystemACService systemAC, SystemGOService systemGO, MainProcessingService main)
	{
		return args -> {
			System.out.println("Enviando Mensagem para o sistemas...");


			systemGO.process("Mensagem vinda do GO");
			systemAC.processSystemAC("Mensagem vinda do AC");
			rabbit.convertAndSend("main.queue");
		};
	}
}
