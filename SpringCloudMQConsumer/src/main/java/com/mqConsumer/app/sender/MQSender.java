package com.mqConsumer.app.sender;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mqConsumer.app.service.ConsumerService;

@Component
public class MQSender {

	@Autowired
	private ConsumerService consumerService;
	
	
	@Scheduled(fixedDelay = 10000)
	   public void sendMessage() {
		String message="consumer wala hu Pahuch gya bhai m  Itne bje ";
		if(consumerService.get().equals("hey"));
		message="hiiiiiii";
		consumerService.sendMessage(message+new Date());
	   }
}
