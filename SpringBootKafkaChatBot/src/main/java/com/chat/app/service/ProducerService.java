package com.chat.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> template;
	
	@Value("my-r")
	private String topicName;
	// @MessageMapping("/send-message")
	//    @SendTo("/topic/messages")
	
	
	public void sendMessage(String message) {
		template.send(topicName,message);
		log.info("Sent SuccessFully {}",message);
		System.out.println("send");
	}
	@Scheduled(fixedDelay = 10000)
	public void send() {
		this.sendMessage("hey");
	}
	
}
