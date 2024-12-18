package com.ms.app.consumer;

import java.net.URI;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartConsumer {

	@Autowired
	private LoadBalancerClient loadBalancer;
	
	public ResponseEntity<String> getCart() {
		
		ServiceInstance sI = loadBalancer.choose("CART-SERVICE");
		URI uri = sI.getUri();
		String url = uri + "/api/cart/";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response =  restTemplate.getForEntity(url, String.class);
		return response;
		
	}
}
