package com.order.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.app.exception.handler.CircuiteBreakerHelper;
import com.order.app.payload.OrderRequest;
import com.order.app.service.IOrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/order")
@CircuitBreaker(name = "inventory",fallbackMethod = "handle")
@TimeLimiter(name="inventory")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	@Autowired
	private CircuiteBreakerHelper breaker;

	// create order
	@PostMapping
	public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequest request) {
		return orderService.createOrder(request);
	}
	
	public ResponseEntity<?> handle(RuntimeException ex){
		return this.breaker.handle(ex);
	}
	

}
