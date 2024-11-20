package com.api.gateway.security;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.api.gateway.exception.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler {

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
		return Mono.defer(() -> {

			ErrorResponse error = new ErrorResponse("FORBIDDEN", HttpStatus.FORBIDDEN);
			exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
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