package com.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsDollopEurekaServerApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(MsDollopEurekaServerApplication.class, args);
		
	}
	
}




