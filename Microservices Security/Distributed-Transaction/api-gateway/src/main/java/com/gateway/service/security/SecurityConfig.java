package com.gateway.service.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
public class SecurityConfig {

	
	@Bean
	MapReactiveUserDetailsService userDetails() {
		UserDetails user = User.withDefaultPasswordEncoder().username("rohit").password("1234").roles("User").build();
		return new MapReactiveUserDetailsService(user);
	}
	
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
      return  httpSecurity
                .authorizeExchange(auth->{
                	auth.anyExchange().authenticated();
             
                }).httpBasic().and().csrf().disable().formLogin().disable().build();
             
    }


}
