package com.payment.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.payment.app.payload.PaymentRequest;

public interface IPaymentService {

	ResponseEntity<Map<String, Object>> createPayment(PaymentRequest paymentRequest);

}
