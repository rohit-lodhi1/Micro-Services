package com.stock.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.app.constants.AppConstants;
import com.stock.app.exception.EmptyInventoryException;
import com.stock.app.model.Inventory;
import com.stock.app.payload.PaymentEvent;
import com.stock.app.payload.StockUpdateRequest;
import com.stock.app.repository.InventoryRepository;
import com.stock.app.service.IInventoryService;

@Service
public class InventoryServiceImpl implements IInventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private KafkaTemplate<String, PaymentEvent> paymentKafkaTemplate;

	private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
	
	@Override
	public ResponseEntity<Map<String, Object>> updateStock(StockUpdateRequest request) {
		Map<String, Object> response = new HashMap<>();
		List<Inventory> inventory = this.inventoryRepository.findByItem(request.getItem());
	
		try {
			if (inventory.isEmpty()) {
				logger.error(AppConstants.INVENTORY_EMPTY);
				throw new Exception(AppConstants.INVENTORY_EMPTY);
			}

			for (Inventory i : inventory) {
				long q = i.getQuantity() - request.getQuantity();
				if (q < 0) {
					logger.error(AppConstants.NOT_ENOUGH_INVENTORY);
					throw new Exception(AppConstants.NOT_ENOUGH_INVENTORY);
				} else {
					i.setQuantity(q);
					this.inventoryRepository.save(i);
				}
			}
			response.put(AppConstants.MESSAGE, AppConstants.STOCK_UPDATE_SUCCESS);
			response.put("stock", inventory);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception e) {
			PaymentEvent p = new PaymentEvent();
			p.setOrderRequest(request);
			p.setType("Payment Reversed");
			logger.error(e.getMessage());
			this.paymentKafkaTemplate.send(AppConstants.REVERSE_PAYMENT, p);
			throw new EmptyInventoryException();
		}

	}

}
