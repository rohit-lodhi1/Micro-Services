package com.chat.app.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerService {

	private Queue<String> messages = new ConcurrentLinkedQueue<>();
	
	
	
	@KafkaListener(topics = "my-r" ,groupId = "abcd")
	public void readMessage(String message) {
		 log.info("message At Consumer {} ",message);
		 messages.add(message);
	}
	
	public Queue<String> getMessages(){
		return messages;
	}
}
