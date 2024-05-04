package com.payment.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.app.payload.OrderEvent;
import com.payment.app.payload.PaymentRequest;
import com.payment.app.service.IPaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	
	
	@Autowired
	private IPaymentService paymentService;
	
	// make payment of order
	@PostMapping
	public ResponseEntity<Map<String, Object>> makePayment(@RequestBody PaymentRequest paymentRequest){
		return paymentService.createPayment(paymentRequest);
	}
}
