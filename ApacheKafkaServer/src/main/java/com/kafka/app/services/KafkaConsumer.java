package com.kafka.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.app.entities.User;

@Service
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(topics = "rohit2",groupId = "myGroup")
	public void consumes(User message) {
		LOGGER.info("Message recieved "+message);
		 System.out.println(message.getUserName());
	}
}
