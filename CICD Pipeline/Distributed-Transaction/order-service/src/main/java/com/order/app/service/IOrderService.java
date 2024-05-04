package com.order.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.order.app.model.Orders;
import com.order.app.payload.OrderRequest;

public interface IOrderService {

	ResponseEntity<Map<String, Object>> createOrder(OrderRequest request);

}
