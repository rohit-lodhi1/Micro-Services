package com.mqConsumer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringCloudMqConsumer2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMqConsumer2Application.class, args);
	}

}
