package com.gatway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsDollopApiGatewayApplication {	

	public static void main(String[] args) {
		SpringApplication.run(MsDollopApiGatewayApplication.class, args);
		
	}
	
}
