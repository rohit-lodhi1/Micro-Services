package com.ms.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.app.consumer.CartConsumer;
import com.ms.app.consumer.FeignConsumer;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private FeignConsumer feignConsumer;
	
	@GetMapping("/")
	public ResponseEntity<String> check(){
         return this.feignConsumer.check();		
	}
	
	
	
}
