package com.gateway.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig extends WebSecurityConfiguration{
	  public static final String[] PERMITTED_URL = new String[]{
	            "/token/get",
	            "/actuator/**"
	    };
	  
	  @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	    @Bean
	    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity serverHttpSecurity) {

	        return serverHttpSecurity
	                .authenticationManager(authenticationManager)
	                .securityContextRepository(securityContextRepository)
	                .csrf(ServerHttpSecurity.CsrfSpec::disable)
	                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
	                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
	                .authorizeExchange(exchanges -> exchanges
	                        .pathMatchers(PERMITTED_URL)
	                        .permitAll()
	                        .anyExchange()
	                        .authenticated()
	                )
	                .build();
	    }
}
