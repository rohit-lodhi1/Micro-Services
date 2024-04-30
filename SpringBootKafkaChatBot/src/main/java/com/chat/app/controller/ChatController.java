package com.chat.app.controller;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.entity.Message;
import com.chat.app.service.ConsumerService;
import com.chat.app.service.ProducerService;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class ChatController {

//	@Autowired
	private ProducerService producerService;
//	
//	@Autowired
	private ConsumerService consumerService;
	
	@GetMapping("/get")
	public ResponseEntity<List<String>> getMessage(){
	List<String> collect = this.consumerService.getMessages().stream().map(e->e.toString()).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(collect,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/send/{message}")
	public ResponseEntity<String> sendMessage(@PathVariable String message){
		System.out.println("adklj");
		this.producerService.sendMessage(message);
		return new ResponseEntity<String>("Sent ",HttpStatus.OK);
	}
	
	
}
