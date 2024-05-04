package com.order.app.external.services;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.app.payload.PaymentRequest;
;

@FeignClient("PAYMENT-SERVICE")
public interface IPaymentService {

	@PostMapping("/payment")
	public ResponseEntity<Map<String, Object>> makePayment(@RequestBody PaymentRequest paymentRequest);
}
