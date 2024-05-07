package com.gateway.service.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationConverter implements ServerAuthenticationConverter {

    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
    	System.err.println(exchange);
    	  return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                  .filter(authHeader -> authHeader.startsWith(BEARER_PREFIX))
                  .map(authHeader -> authHeader.substring(BEARER_PREFIX.length()))
                  .map(token -> new UsernamePasswordAuthenticationToken(token, token));
    }
}