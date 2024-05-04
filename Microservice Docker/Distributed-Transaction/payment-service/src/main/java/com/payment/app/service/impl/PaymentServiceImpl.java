package com.payment.app.service.impl;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.app.constants.AppConstants;
import com.payment.app.exception.PaymentFailedException;
import com.payment.app.external.service.IInventoryService;
import com.payment.app.model.Payment;
import com.payment.app.model.PaymentStatus;
import com.payment.app.payload.ErrorMessage;
import com.payment.app.payload.OrderEvent;
import com.payment.app.payload.PaymentEvent;
import com.payment.app.payload.PaymentRequest;
import com.payment.app.payload.StockUpdateRequest;
import com.payment.app.repository.PaymentRepository;
import com.payment.app.service.IPaymentService;

import feign.FeignException.FeignClientException;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

	@Autowired
	private IInventoryService inventoryService;

	@Autowired
	private ModelMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private KafkaTemplate<String, OrderEvent> orderKafkaTemplate;

	@Override
	public ResponseEntity<Map<String, Object>> createPayment(PaymentRequest paymentRequest) {
		Map<String, Object> response = new HashMap<>();

		Payment payment = new Payment();
		payment.setAmount(paymentRequest.getAmount());
		payment.setMode(paymentRequest.getPaymentMode());
		payment.setOrderId(paymentRequest.getOrderId());
		payment.setStatus(PaymentStatus.SUCCESS);
		try {
			payment = paymentRepository.save(payment);

			logger.info(AppConstants.UPDATING_STOCK, payment.getOrderId());

			this.inventoryService.updateStock(this.mapper.map(paymentRequest, StockUpdateRequest.class));
			
			response.put(AppConstants.MESSAGE, AppConstants.PAYMENT_SUCCESS);
			response.put(AppConstants.PAYMENT, payment);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (FeignClientException e) {
			payment.setOrderId(paymentRequest.getOrderId());
			payment.setStatus(PaymentStatus.FAILED);
			payment = paymentRepository.save(payment);
			
			OrderEvent rollBack = new OrderEvent();
			rollBack.setOrderRequest(paymentRequest);
			rollBack.setType("Order Reversed");
			
			logger.error(e.contentUTF8());
			
			orderKafkaTemplate.send(AppConstants.REVERSE_ORDER, rollBack);
			
			ErrorMessage error = new ErrorMessage();
			try {
				error = new ObjectMapper().readValue(e.contentUTF8(), ErrorMessage.class);
			} catch (JsonMappingException e1) {

				e1.printStackTrace();
			} catch (JsonProcessingException e1) {

				e1.printStackTrace();
			}
			throw new PaymentFailedException(error.getMessage());
		}
	}

}
