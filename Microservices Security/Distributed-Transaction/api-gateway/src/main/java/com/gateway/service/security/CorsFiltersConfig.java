package com.api.gateway.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsFiltersConfig {

	
	private static final String AUTHORIZATION = "Authorization";

	private String adminAllowedOrigins;

	private String portalOfficerOrigins;

	
	private String verificationOfficerOrigins;

	private String emergencyOfficerOrigins;

	private String apiDocUserOrigins;

	
	private String userOrigins;
	
	

	/**
	 * Configures CORS based on allowed origins for different roles.
	 *
	 * @return CorsConfigurationSource containing CORS configurations for different
	 *         roles.
	 */
	
//	@Bean
//     CorsWebFilter corsWebFilter() {
//		List<String> origins = new ArrayList<>();
//		origins.addAll(Arrays.asList(this.adminAllowedOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.apiDocUserOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.verificationOfficerOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.portalOfficerOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.emergencyOfficerOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.userOrigins.split(",")));
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedOrigins(origins); // or specify your allowed origins
//        corsConfig.addAllowedMethod("*");
//        corsConfig.addAllowedHeader("*");
//        corsConfig.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsWebFilter(source);
//    }
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		// CORS configuration for ADMIN role
//		this.registerAdminOrigins(source);

//		// CORS configuration for Portal Officer role
//		this.registerPortalOfficerOrigins(source);
//
//		// CORS configuration for Verification Officer role
//		this.registerVerificationOfficerOrigins(source);
//
//		// CORS configuration for Emergency Officer role
//		this.registerEmergencyOfficerOrigins(source);
//
//		// CORS configuration for Api Doc User role
//		this.registerApiDocOrigins(source);
//		
//		this.registerUserOrigins(source);
		
		this.addAllOrigins(source);

		return source;
	}

	private void registerAdminOrigins(UrlBasedCorsConfigurationSource source) {
		CorsConfiguration adminCorsConfig = new CorsConfiguration();
		List<String> origins = Arrays.asList(this.adminAllowedOrigins.split(","));
		origins.forEach(adminCorsConfig::addAllowedOrigin);
		adminCorsConfig.setAllowedMethods(Arrays.asList("*"));
		adminCorsConfig.setAllowedHeaders(Arrays.asList(AUTHORIZATION));
		adminCorsConfig.setAllowCredentials(true);
		source.registerCorsConfiguration("/admin/**", adminCorsConfig);
	}

	private void registerVerificationOfficerOrigins(UrlBasedCorsConfigurationSource source) {
		CorsConfiguration verificationOfficerCorsConfig = createCorsConfiguration(this.verificationOfficerOrigins);
		source.registerCorsConfiguration("/**", verificationOfficerCorsConfig);
	}

	private void registerEmergencyOfficerOrigins(UrlBasedCorsConfigurationSource source) {
		CorsConfiguration emergencyOfficerCorsConfig = createCorsConfiguration(this.emergencyOfficerOrigins);
		source.registerCorsConfiguration("/**", emergencyOfficerCorsConfig);
	}

	private void registerPortalOfficerOrigins(UrlBasedCorsConfigurationSource source) {
		CorsConfiguration portalOfficerCorsConfig = createCorsConfiguration(this.portalOfficerOrigins);
		source.registerCorsConfiguration("/**", portalOfficerCorsConfig);
	}

	private void registerUserOrigins(UrlBasedCorsConfigurationSource source) {
		CorsConfiguration userCorsConfig = createCorsConfiguration(this.userOrigins);
		source.registerCorsConfiguration("/**", userCorsConfig);
	}

	private void registerApiDocOrigins(UrlBasedCorsConfigurationSource source) {
		CorsConfiguration apiDocUserCorsConfig = createCorsConfiguration(this.apiDocUserOrigins);
		source.registerCorsConfiguration("/**", apiDocUserCorsConfig);
	}

	private void registerPublicOrigins(UrlBasedCorsConfigurationSource source) {
		CorsConfiguration publicCorsConfig = new CorsConfiguration();
		publicCorsConfig.addAllowedOrigin("*");
		publicCorsConfig.setAllowedMethods(Arrays.asList("*"));
		publicCorsConfig.setAllowedHeaders(Arrays.asList(AUTHORIZATION));
		publicCorsConfig.setAllowCredentials(false);
		source.registerCorsConfiguration("/**", publicCorsConfig);
	}
	
	
	private void addAllOrigins(UrlBasedCorsConfigurationSource  source) {
		List<String> origins = new ArrayList<>();
//		origins.addAll(Arrays.asList(this.adminAllowedOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.apiDocUserOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.verificationOfficerOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.portalOfficerOrigins.split(",")));
//		origins.addAll(Arrays.asList(this.emergencyOfficerOrigins.split(",")));

//		origins.addAll(Arrays.asList(this.userOrigins.split(",")));

		origins.addAll(Arrays.asList("*"));
		CorsConfiguration corsConfig = new CorsConfiguration();
	
		corsConfig.setAllowedOrigins(origins);
		
		
		corsConfig.setAllowedMethods(Arrays.asList("*"));
		corsConfig.setAllowedHeaders(Arrays.asList("*"));
		corsConfig.setAllowCredentials(false);
		source.registerCorsConfiguration("/**", corsConfig);
	}

	private CorsConfiguration createCorsConfiguration(String origins) {
		CorsConfiguration corsConfig = new CorsConfiguration();
		List<String> originList = Arrays.asList(origins.split(","));
		originList.forEach(corsConfig::addAllowedOrigin);
		corsConfig.setAllowedMethods(Arrays.asList("*"));
		corsConfig.setAllowedHeaders(Arrays.asList(AUTHORIZATION));
		corsConfig.setAllowCredentials(true);
		return corsConfig;
	}
}
