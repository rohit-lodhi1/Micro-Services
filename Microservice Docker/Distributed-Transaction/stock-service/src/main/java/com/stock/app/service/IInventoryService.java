package com.stock.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.stock.app.payload.StockUpdateRequest;

public interface IInventoryService {

	ResponseEntity<Map<String, Object>> updateStock(StockUpdateRequest request);

}
