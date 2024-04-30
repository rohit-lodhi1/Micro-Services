package com.kafka.app;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class ApacheKafkaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheKafkaServerApplication.class, args);
	}

	
}
