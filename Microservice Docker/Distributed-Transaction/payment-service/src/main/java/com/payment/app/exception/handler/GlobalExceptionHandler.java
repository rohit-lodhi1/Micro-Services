package com.payment.app.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.payment.app.exception.PaymentFailedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PaymentFailedException.class)
	public ResponseEntity<Map<String, Object>> errorWhileProcessing(PaymentFailedException e){
		Map<String, Object> response = new HashMap<>();
		response.put("message", e.getMessage());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	}
	
}
