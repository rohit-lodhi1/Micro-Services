package com.payment.app.service.impl.reverse;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.app.constants.AppConstants;
import com.payment.app.model.Payment;
import com.payment.app.model.PaymentStatus;
import com.payment.app.payload.OrderEvent;
import com.payment.app.payload.PaymentEvent;
import com.payment.app.repository.PaymentRepository;
import com.payment.app.service.impl.PaymentServiceImpl;

@Component
public class ReversePaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(ReversePaymentService.class);
	
	@KafkaListener(topics = AppConstants.REVERSE_PAYMENT,groupId = AppConstants.PAYMENT_GROUP)
	public void reversePayment(String event) throws Exception {
		logger.info(AppConstants.PAYMENT_REVERSE_EVENT,event);
		try {
			PaymentEvent paymentEvent = new ObjectMapper().readValue(event, PaymentEvent.class);
			
			List<Payment> payments = this.paymentRepository.findByOrderId(paymentEvent.getOrderRequest().getOrderId());
			payments.forEach(p ->{
				p.setStatus(PaymentStatus.FAILED);
				paymentRepository.save(p);
			});
			
			OrderEvent orderEvent = new OrderEvent(paymentEvent.getOrderRequest(),"Order Reversed");
			kafkaTemplate.send(AppConstants.REVERSE_ORDER,orderEvent);
		} catch (Exception e) {
           e.printStackTrace();
		}
	}
}
