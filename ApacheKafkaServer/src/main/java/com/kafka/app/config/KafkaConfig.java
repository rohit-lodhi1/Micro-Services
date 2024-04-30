package com.kafka.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
	@Bean
	public NewTopic rohitTopic() {
		return TopicBuilder.name("rohit2").build();
	}
}
