//package com.server.app.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Value("${eureka.username}")
//	private String username;
//	@Value("${eureka.password}")
//	private String password;
//	
//	
//	
//	@Override
//	public void configure(AuthenticationManagerBuilder builder) throws Exception {
//		builder.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser(username).password(password).authorities("Admin");
//	}
//
//	@Override
//	public void configure(HttpSecurity security) throws Exception {
//		security.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//	}
//}
