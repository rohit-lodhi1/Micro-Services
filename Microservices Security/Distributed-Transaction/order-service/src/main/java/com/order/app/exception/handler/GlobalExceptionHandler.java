package com.order.app.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.order.app.constants.AppConstants;
import com.order.app.exception.ErrorWhileProcessing;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ErrorWhileProcessing.class)
	public ResponseEntity<Map<String, Object>> errorWhileProcessing(ErrorWhileProcessing e){
		Map<String, Object> response = new HashMap<>();
		response.put(AppConstants.MESSAGE, e.getMessage());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	}
	
}
