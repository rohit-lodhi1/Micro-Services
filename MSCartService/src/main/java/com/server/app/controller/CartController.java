package com.server.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@GetMapping("/")
	public ResponseEntity<String> check(){
		return ResponseEntity.ok("yes GEt APi");
	}
	
}
