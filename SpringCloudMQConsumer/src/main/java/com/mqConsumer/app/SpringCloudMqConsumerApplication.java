package com.mqConsumer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringCloudMqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMqConsumerApplication.class, args);
	}

}
