package com.api.gateway.exception.handler;

import java.net.ConnectException;

import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.api.gateway.exception.ErrorResponse;
import com.api.gateway.exception.UnauthorizedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
@Order(-2) // Specify the order of this handler
public class GlobalExceptionHandler implements WebExceptionHandler {

	private static final String SERVICE_UNAVAILABLE = "Service Unavailable";
	private static final String UNAUTHORIZED = "Access Denied";
	private static final String FORBIDDEN = "Forbidden";
	
	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		String json = "";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		if (ex instanceof WebClientRequestException || ex instanceof WebClientResponseException.ServiceUnavailable
				|| ex instanceof NotFoundException || ex instanceof ConnectException) {
			json = this.getResponse(HttpStatus.SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE);
			status = HttpStatus.SERVICE_UNAVAILABLE;
		}

		else if (ex instanceof WebClientResponseException.InternalServerError) {
			WebClientResponseException.BadRequest exception = (WebClientResponseException.BadRequest) ex;
			json = exception.getResponseBodyAsString();

		} else if (ex instanceof WebClientResponseException.Unauthorized || ex instanceof UnauthorizedException) {
			json = this.getResponse(HttpStatus.UNAUTHORIZED, UNAUTHORIZED);
			status = HttpStatus.UNAUTHORIZED;
		}

		else if (ex instanceof WebClientResponseException.Forbidden) {
			json = this.getResponse(HttpStatus.FORBIDDEN, FORBIDDEN);
			status = HttpStatus.FORBIDDEN;
		}
		else if (ex instanceof WebClientResponseException.BadRequest badRequest) {

			json = badRequest.getResponseBodyAsString();
			status = HttpStatus.BAD_REQUEST;
		}
		
	  
	  

		// Handle other exceptions if needed
		ex.printStackTrace();
		exchange.getResponse().setStatusCode(status);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return exchange.getResponse()
				.writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(json.getBytes())));
	}

	public String getResponse(HttpStatus status, String message) {
		ErrorResponse errorResponse = new ErrorResponse(message, status);

		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(errorResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
