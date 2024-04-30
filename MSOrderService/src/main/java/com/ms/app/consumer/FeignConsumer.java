package com.ms.app.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="CART-SERVICE")
public interface FeignConsumer {
	@GetMapping("/api/cart/")
	public ResponseEntity<String> check();
}
