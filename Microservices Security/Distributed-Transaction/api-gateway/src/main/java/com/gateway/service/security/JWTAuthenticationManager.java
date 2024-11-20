package com.api.gateway.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class JWTAuthenticationManager implements ReactiveAuthenticationManager {

	private final UserDetailsService userDetailsService;


	public JWTAuthenticationManager(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String token = authentication.getCredentials().toString();

		String action = "";
		if (authentication.getPrincipal() != null)
			action = authentication.getPrincipal().toString();
		return userDetailsService.findByUsername(token, action)
				.map(userDetails -> new UsernamePasswordAuthenticationToken(userDetails,null,
						userDetails.getAuthorities()));
	}
}