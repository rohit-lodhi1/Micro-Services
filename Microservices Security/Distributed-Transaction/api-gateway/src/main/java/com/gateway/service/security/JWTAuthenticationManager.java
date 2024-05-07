package com.gateway.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class JWTAuthenticationManager implements ReactiveAuthenticationManager {

    private final UserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;
    

    public JWTAuthenticationManager(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        // Validate token
        // Extract username from token
      //  String username = // extract from token
        String username =(String) jwtUtil.getUserId(token, "username");
        System.err.println(username);
        return userDetailsService.findByUsername(username)
                .map(userDetails -> new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }
}
