package com.api.gateway.security;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.gateway.support.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.api.gateway.external.service.AuthenticationService;
import com.api.gateway.payloads.UserDetailsResponse;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsService implements ReactiveUserDetailsService {

	private final ModelMapper mapper;

	private final AuthenticationService authenticationService;

	public UserDetailsService( ModelMapper mapper,
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
		this.mapper = mapper;
	}

	private final  String SERVICE_UNAVAILABLE = "Service Unavailable";

	@Override
	public Mono<UserDetails> findByUsername(String token) {

		Mono<ResponseEntity<Object>> validateToken = this.authenticationService.validateToken(token, "");
		return validateToken.flatMap(response -> {
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

				UserDetailsResponse user = mapper.map(response.getBody(), UserDetailsResponse.class);

				SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(user.getAuthority());

				return Mono.just(org.springframework.security.core.userdetails.User.builder()
						.username(user.getUsername()).password(user.getPassword()).authorities(authorities).build());
			} else if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) 
				return Mono.error(new ServiceUnavailableException(SERVICE_UNAVAILABLE));
			 else 
				return Mono.empty(); // Invalid token, return empty Mon
			
		}).onErrorResume(WebClientRequestException.class, e -> {
			// Handle WebClientRequestException
			return Mono.error(e);
		});
	}

	public Mono<UserDetails> findByUsername(String token,String action) {

        System.err.println("service se"+action);
		Mono<ResponseEntity<Object>> validateToken = this.authenticationService.validateToken(token,action);
		return validateToken.flatMap(response -> {
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

				UserDetailsResponse user = mapper.map(response.getBody(), UserDetailsResponse.class);
System.out.println(user.getUsername());
				SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(user.getAuthority());

				return Mono.just(org.springframework.security.core.userdetails.User.builder()
						.username(user.getUsername()).password(user.getPassword()).authorities(authorities).build());
			} else if (response.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
				return Mono.error(new ServiceUnavailableException(SERVICE_UNAVAILABLE));
			} else {
				return Mono.empty(); // Invalid token, return empty Mon
			}
		}).onErrorResume(WebClientRequestException.class, e -> {
			// Handle WebClientRequestException
			e.printStackTrace();
			return Mono.error(e);

		});
	}

}
