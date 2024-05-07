package com.gateway.service.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import reactor.core.publisher.Mono;


@EnableWebFluxSecurity
public class SecurityConfig {
	

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService  userDetails;
	
	@Autowired
	private JWTAuthenticationManager authManager;

	
//	@Bean
//	MapReactiveUserDetailsService userDetails() {
//		UserDetails user = User.builder().username("rohit").password(passwordEncoder.encode("1234")).roles("User").build();
//		return new MapReactiveUserDetailsService(user);
//	}
	
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
      return  httpSecurity
                .authorizeExchange(auth->{
                	auth.pathMatchers("/user/auth/login").permitAll().pathMatchers("/user/auth/me").hasAuthority("User").anyExchange().authenticated();
                	
          
                }).httpBasic().disable().csrf().disable().formLogin().disable()	.logout().disable()
                .addFilterAt(authenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                
//                .exceptionHandling()
//                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
//                .accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))).and().build();
                .build();
             
    }

    private AuthenticationWebFilter authenticationFilter() {
        AuthenticationWebFilter authenticationFilter = new AuthenticationWebFilter(authManager);
        authenticationFilter.setServerAuthenticationConverter(new AuthenticationConverter());
//        authenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/secured/**"));
        return authenticationFilter;
    }

}
