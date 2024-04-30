package com.mq.app.service;

import java.util.Date;

import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

	
//	@JmsListener(destination = "my-s")
//	public void readMessage(String message) {
//		System.out.println(message);
//	}
//	
	
	@Autowired
	private JmsTemplate jt;
	
	@Value("${my.app.desti-name}")
	private String destination;
	
	public void sendMessage(String message) {
		
		
		jt.send(destination,session->session.createTextMessage(message+new Date()));
		System.out.println("Sent Successfully - "+message+new Date());
	}
	
}
