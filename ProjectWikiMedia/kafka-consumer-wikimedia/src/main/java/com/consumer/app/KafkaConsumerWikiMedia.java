package com.consumer.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.consumer.app.entity.WikiMediaData;
import com.consumer.app.repo.WikiMediaDataRepository;

@Service
public class KafkaConsumerWikiMedia {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerWikiMedia.class);
	
	private WikiMediaDataRepository dataRepository;
	
	@KafkaListener(topics = "rohit",groupId = "myGroup")
	public void consume(String message) {
		LOGGER.info("Message Received +"+message);
		WikiMediaData media = new WikiMediaData();
		media.setWikiEventData(message);
		dataRepository.save(media);
	}
	
}
