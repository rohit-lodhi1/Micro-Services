package com.kafka.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.app.entities.User;
import com.kafka.app.services.KafkaProducer;

@RestController
@RequestMapping("/server")
@CrossOrigin("*")
public class KafkaServerController {

	@Autowired
	private KafkaProducer producerService;
	
	@PostMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestBody String message){
		this.producerService.sendMessage(message);
		return new ResponseEntity<String>("Message has sent successfully "+message,HttpStatus.OK);
	}
	
}
