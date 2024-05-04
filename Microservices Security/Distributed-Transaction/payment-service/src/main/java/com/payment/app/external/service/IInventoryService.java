package com.payment.app.external.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.payment.app.payload.StockUpdateRequest;


@FeignClient("INVENTORY-SERVICE")
public interface IInventoryService {
	
	@PostMapping("/inventory")
	public ResponseEntity<Map<String, Object>> updateStock(@RequestBody StockUpdateRequest request);

}
