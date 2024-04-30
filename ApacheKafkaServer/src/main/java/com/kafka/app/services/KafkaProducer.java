package com.kafka.app.services;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kafka.app.entities.User;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplateForObject;
	
	@Value("${my.topic.name}")
	private String topic;
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(KafkaProducer.class);
	
	public void sendMessage(String message) {
		LOGGER.info("Message has been sent -> "+message);
		kafkaTemplate.send(topic, message);
		System.out.println("done danan don");
	}
	public void sendMessage(User message) {
		LOGGER.info("Message has been sent -> "+message);
		Message<User> mes= MessageBuilder.withPayload(message)
				           .setHeader(KafkaHeaders.TOPIC, "checking ")
				           .build();
		kafkaTemplateForObject.send(mes);
		
	}
}
