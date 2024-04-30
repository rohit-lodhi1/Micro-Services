package com.gatway.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class SpringCloudAdminServerGuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAdminServerGuiApplication.class, args);
	}

}
