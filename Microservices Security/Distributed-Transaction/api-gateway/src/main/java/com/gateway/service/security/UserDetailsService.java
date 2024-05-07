package com.gateway.service.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsService implements ReactiveUserDetailsService{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		// 
		SimpleGrantedAuthority authorities = new SimpleGrantedAuthority("user");
		return Mono.just(User.builder().username("rohit").password(passwordEncoder.encode("1234")).authorities(authorities).build());
	}

}
