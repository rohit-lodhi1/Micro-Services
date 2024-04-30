package com.gatway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringCloudSechudlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSechudlingApplication.class, args);
	}

}
