
package com.order.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.app.config.JwtUtil;

@RestController
@RequestMapping("/user/auth")
public class UserController {

	@Autowired
	private JwtUtil jwtUtils;
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username") String username,@RequestParam("password") String password) {
		
		return new ResponseEntity<>(jwtUtils.generateToken(username, "user"),HttpStatus.OK);
	}
	
	@GetMapping("/me")
	public ResponseEntity<String> secured(){
		return new ResponseEntity<>("secured ",HttpStatus.OK);
	}
}
