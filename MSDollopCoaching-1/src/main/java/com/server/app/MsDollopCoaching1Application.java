package com.server.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@RefreshScope	
public class MsDollopCoaching1Application {

	public static void main(String[] args) {
		SpringApplication.run(MsDollopCoaching1Application.class, args);
		System.out.println(5*0.5==0.5);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
