package com.stock.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.app.payload.PaymentEvent;
import com.stock.app.payload.StockUpdateRequest;
import com.stock.app.service.IInventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private IInventoryService inventoryService;
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> updateStock(@RequestBody StockUpdateRequest request){
		return this.inventoryService.updateStock(request);
	}
	
}  
