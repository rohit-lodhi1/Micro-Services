package com.api.gateway.external.service;


import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class AuthenticationService {


    private final WebClient.Builder webClientBuilder;

	private static final String AUTH_URL ="http://AUTHENTICATION-SERVICE/auth/validate";
	
	private static final String ACTION="Action";

	Logger logger = Logger.getLogger(getClass().getName());

	public AuthenticationService(WebClient.Builder webClientBuilder) {
		this.webClientBuilder=webClientBuilder;
	}

	public Mono<ResponseEntity<Object>> validateToken(String token,String url) {
		logger.info(url);
	    return webClientBuilder.build().get()
	            .uri(AUTH_URL, token)
	            .header(ACTION, url)
	            .header(HttpHeaders.AUTHORIZATION, token) // Set the Authorization header
	            .retrieve()
//	            .onStatus(HttpStatus::is4xxClientError,
//	                    response -> Mono.error(new UnauthorizedException("Unauthorized access")))
	            .toEntity(Object.class);
	}
}