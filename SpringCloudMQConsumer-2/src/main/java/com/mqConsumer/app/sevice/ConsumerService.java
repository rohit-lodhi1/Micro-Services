package com.mqConsumer.app.sevice;

import java.util.Date;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {
	@JmsListener(destination = "${my.app.desti-name}")
	public void readMessage(String message) {
		System.out.println("Consumer 1 -"+message);
	
	}
}
