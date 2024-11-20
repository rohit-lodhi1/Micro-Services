package com.api.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	private final JWTAuthenticationManager authManager;
	
	@Autowired
	private final BCryptPasswordEncoder passwordEncoder;

	private final CorsFiltersConfig corsFiltersConfig;
	
	public SecurityConfig(JWTAuthenticationManager authManager, JwtUtils jwtUtil,

			 BCryptPasswordEncoder encoder,

			CustomAuthenticationEntryPoint authenticationEntryPoint, CustomAccessDeniedHandler accessDeniedHandler,
			CorsFiltersConfig corsFiltersConfig) {

		this.accessDeniedHandler = accessDeniedHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authManager = authManager;
		this.passwordEncoder=encoder;
		this.corsFiltersConfig = corsFiltersConfig;
	}

	private final CustomAuthenticationEntryPoint authenticationEntryPoint;

	private final CustomAccessDeniedHandler accessDeniedHandler;

	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		return httpSecurity.authorizeExchange(auth -> auth.pathMatchers(EndPoints.PERMIT_ALL).permitAll()
				.pathMatchers(EndPoints.AGENCY_SUPER_ADMIN).hasAuthority(Role.AGENCY_SUPER_ADMIN.toString())
				.pathMatchers(EndPoints.AGENCY_RELATIONSHIP_MANAGER)
				.hasAuthority(Role.AGENCY_RELATIONSHIP_MANAGER.toString()).pathMatchers(EndPoints.AGENCY_ADMIN)
				.hasAuthority(Role.AGENCY_ADMIN.toString()).pathMatchers(EndPoints.AGENCY_TEAM_MEMEBER)
				.hasAuthority(Role.AGENCY_TEAM_MEMBER.toString()).pathMatchers(EndPoints.CUSTOMER_ADMIN)
				.hasAuthority(Role.CUSTOMER_ADMIN.toString()).pathMatchers(EndPoints.CUSTOMER_TEAM_MEMBER)
				.hasAuthority(Role.CUSTOMER_TEAM_MEMBER.toString()).anyExchange().authenticated()

		).httpBasic(basic -> basic.disable()
				.csrf(csrf -> csrf.disable().formLogin(login -> login.disable().logout(logout -> logout.disable()
						.addFilterBefore(authenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)

						.exceptionHandling(handling -> handling.authenticationEntryPoint(authenticationEntryPoint)
								.accessDeniedHandler(accessDeniedHandler))))))
               .cors(cors -> cors.configurationSource(corsFiltersConfig.corsConfigurationSource()))
				.build();
	}

	private AuthenticationWebFilter authenticationFilter() {
		AuthenticationWebFilter authenticationFilter = new AuthenticationWebFilter(authManager);
		authenticationFilter.setServerAuthenticationConverter(new AuthenticationConverter());

//        authenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/secured/**"));
		return authenticationFilter;
	}
}
