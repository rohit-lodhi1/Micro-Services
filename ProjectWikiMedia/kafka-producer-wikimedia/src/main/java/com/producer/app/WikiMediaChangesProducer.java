package com.producer.app;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class WikiMediaChangesProducer {

	private final static Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${my.topic.name}")
	private String topic;
	
	public void send() {
		
		
		// to read real time stream data from wikimedia, we use event source
		EventHandler handler = new WikiMediaChangesHandler(kafkaTemplate,topic);
		
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		
		EventSource.Builder builder = new EventSource.Builder(handler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
