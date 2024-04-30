package com.mqConsumer.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

	String mes="";
	
	@JmsListener(destination = "${my.app.desti-name}")
	public void readMessage(String message) {
		System.out.println(message);
		mes=message;
	}
	
	public String get() {
		return mes;
	}
	
	
	
	@Autowired
	private JmsTemplate jt;
	
//	@Value("${my.app.desti-name}")
	private String destination="my-s";
	
	
	public void sendMessage(String message) {
	
		jt.send(destination,session->session.createTextMessage(message));
		System.out.println("consumer Sending  - "+message);
	}
	
	
}
