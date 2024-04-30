package com.gatway.app.sechedular;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FetchLatesPropertiesFile {

	@Scheduled(cron = "10 * * * * *")
	public void fetchData() {
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<String> entity = new HttpEntity<String>("{}",headers);
		 RestTemplate restTemplate = new RestTemplate();
		 String body = restTemplate.postForEntity("http://localhost:8083/actuator/refresh",entity,String.class).getBody();
		 System.out.println(body);
	}
}
