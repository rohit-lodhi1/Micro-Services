package com.api.gateway;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import com.api.gateway.security.JwtUtils;

@SpringBootApplication
public class ApiGatewayApplication {
	private final Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	public ApiGatewayApplication(Environment environment) {
		this.environment = environment;
	}


	@Bean
	@LoadBalanced
	WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	JwtUtils jwtUtil() {
		return new JwtUtils(environment.getProperty("secret.key"));
	}

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}

}
