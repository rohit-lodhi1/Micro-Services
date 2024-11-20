package com.api.gateway.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import com.api.gateway.exception.UnauthorizedException;

import reactor.core.publisher.Mono;

@Service
public class AuthenticationConverter implements ServerAuthenticationConverter {

	private static final String BEARER_PREFIX = "Bearer ";
	private static final String ACTION = "Action";

	// No-argument constructor
	public AuthenticationConverter() {
		// Initialization code for the default case
	}

	@Override
	public Mono<Authentication> convert(ServerWebExchange exchange) {
		String tokenFromHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		String action = exchange.getRequest().getURI().getPath().toString();



//        System.err.println("token convertar"+   tokenFromHeader);
//        System.out.println(exchange.getRequest().getHeaders());
    if(tokenFromHeader!=null && !tokenFromHeader.startsWith(BEARER_PREFIX))
    return	Mono.error(new UnauthorizedException());

		if (tokenFromHeader != null && !tokenFromHeader.startsWith(BEARER_PREFIX))
			return Mono.error(new UnauthorizedException());

		return Mono.justOrEmpty(tokenFromHeader).filter(authHeader -> authHeader.startsWith(BEARER_PREFIX))
				.map(token -> new UsernamePasswordAuthenticationToken(action, token));
	}
}