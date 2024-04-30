package com.chat.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootKafkaChatBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaChatBotApplication.class, args);
	}

}
