package com.api.gateway.security;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.api.gateway.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

	@Override
	public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
		return Mono.defer(() -> {
			
			ErrorResponse error = new ErrorResponse("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
			e.printStackTrace();
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
			String errorMessage = "";
			ObjectMapper mapper = new ObjectMapper();
			try {
				errorMessage = mapper.writeValueAsString(error);
			} catch (JsonProcessingException ex) {
				ex.printStackTrace();
			}

			byte[] bytes = errorMessage.getBytes(StandardCharsets.UTF_8);
			return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
		});
	}
}