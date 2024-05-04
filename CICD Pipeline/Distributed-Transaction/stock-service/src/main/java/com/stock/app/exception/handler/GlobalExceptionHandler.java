package com.stock.app.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stock.app.exception.EmptyInventoryException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyInventoryException.class)
	public ResponseEntity<Map<String, Object>> errorWhileProcessing(EmptyInventoryException e){
		Map<String, Object> response = new HashMap<>();
		response.put("message", e.getMessage());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	}
	
}
