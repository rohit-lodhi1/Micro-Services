package com.mqConsumer.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.mqConsumer.app.db.MessageStore;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerService {

	@Autowired
	private MessageStore ms;
	
	@KafkaListener(topics = "${my.topic.name}" ,groupId = "abcd")
	public void readMessage(String message) {
		 log.info("message At Consumer {} ",message);
		 this.ms.add(message);
	}
	
}
