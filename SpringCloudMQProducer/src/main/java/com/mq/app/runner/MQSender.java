package com.mq.app.runner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mq.app.service.ProducerService;

@Component
public class MQSender {

	
	@Autowired
	private ProducerService producerService;
	
	
	@Scheduled(fixedDelay = 10000)
   public void sendMessage() {
	   //producerService.sendMessage("Pahuch gya bhai m  Itne bje "+new Date());
	   producerService.sendMessage("hey");
   }
	

}
