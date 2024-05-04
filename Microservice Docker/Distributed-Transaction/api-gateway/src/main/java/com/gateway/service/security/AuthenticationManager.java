package com.gateway.service.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private static final String DEFAULT_ROLE = "ROLE_USER";
//    private final JwtService tokenService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
    	   return Mono.just(authentication.getCredentials().toString())
                   .flatMap(this::verifyToken)
                   .flatMap(this::getAuthentication);
    }

	
	  private Mono<Authentication> getAuthentication(Object
	  appUser) { return null; }
	 

    private Collection<SimpleGrantedAuthority> getGrantedAuthorities() {

        return List.of(new SimpleGrantedAuthority(DEFAULT_ROLE));
    }

	
	  private Mono<Object> verifyToken(String token) {
	  
	  return null;}
	 

	/*
	 * private Mono<AuthenticationTokenData> getAppUser(DecodedJWT decodedJWT,
	 * String token) { return Mono.just(AuthenticationTokenData.builder()
	 * .userAudience(decodedJWT.getAudience().get(0))
	 * .userId(decodedJWT.getClaim(TOKEN_CLAIM_USER).asString())
	 * .deviceId(decodedJWT.getClaim(TOKEN_CLAIM_DEVICE).asString()) .token(token)
	 * .build() ); }
	 */
}