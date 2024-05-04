package com.order.app.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import feign.FeignException.FeignClientException;

@Component
public class CircuiteBreakerHelper {

	public ResponseEntity<?> handle(Exception ex) {
		
		if(ex instanceof FeignClientException) {
			FeignClientException e=(FeignClientException) ex;
           return new ResponseEntity<>(HttpStatus.valueOf(e.status()));			
		}
		   return new ResponseEntity<>("Payment service is unavailable",HttpStatus.SERVICE_UNAVAILABLE);
	}
}
