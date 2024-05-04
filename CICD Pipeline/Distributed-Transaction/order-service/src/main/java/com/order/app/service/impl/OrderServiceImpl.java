package com.order.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order.app.model.Orders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.app.constants.AppConstants;
import com.order.app.exception.ErrorWhileProcessing;
import com.order.app.external.services.IPaymentService;
import com.order.app.model.OrderStatus;
import com.order.app.payload.ErrorMessage;
import com.order.app.payload.OrderEvent;
import com.order.app.payload.OrderRequest;
import com.order.app.payload.PaymentRequest;
import com.order.app.repository.OrderRepositoy;
import com.order.app.service.IOrderService;

import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepositoy orderRepository;

	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private ModelMapper mapper;

	private static final  Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	// create order
	@Override
	public ResponseEntity<Map<String, Object>> createOrder(OrderRequest request) {
		Map<String, Object> response = new HashMap<>();
		Orders order = new Orders();
		order.setAmount(request.getAmount());
		order.setItem(request.getItem());
		order.setQuantity(request.getQuantity());
		order.setStatus(OrderStatus.CREATED);
//		try {
			order = orderRepository.save(order);

			// sending on topic to make payment by payment service
			request.setOrderId(order.getId());

			// making the payments
			paymentService.makePayment(mapper.map(request, PaymentRequest.class));

			logger.info(AppConstants.MAKE_PAYMENT);
			response.put(AppConstants.MESSAGE, AppConstants.ORDER_CREATED);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//		} catch (FeignClientException e) {
//			
//			logger.error(e.contentUTF8());
//			order.setStatus(OrderStatus.FAILED);
//			order = orderRepository.save(order);
//			
//			response.put(AppConstants.MESSAGE, AppConstants.ORDER_FAILED);
//			
//			ErrorMessage error = new ErrorMessage("Error while Processing");
//			try {
//				error = new ObjectMapper().readValue(e.contentUTF8(), ErrorMessage.class);
//			} catch (JsonMappingException e1) {
//				e1.printStackTrace();
//			} catch (JsonProcessingException e1) {
//				e1.printStackTrace();
//			}    
//			throw new ErrorWhileProcessing(error.getMessage());
//		}
	}

}
