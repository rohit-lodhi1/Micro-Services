package com.order.app.service.impl.reverse;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.app.constants.AppConstants;
import com.order.app.model.OrderStatus;
import com.order.app.model.Orders;
import com.order.app.payload.OrderEvent;
import com.order.app.repository.OrderRepositoy;
import com.order.app.service.impl.OrderServiceImpl;

@Component
public class ReverseOrderService {

	@Autowired
	private OrderRepositoy orderRepository;
	
	private static final  Logger logger = LoggerFactory.getLogger(ReverseOrderService.class);
	
	// listening if payment service failed 
	@KafkaListener(topics = AppConstants.REVERSE_ORDER,groupId =AppConstants.GROUP_ORDER)
	public void reverseOrder(String event) {
		System.out.println("Reverse Order Event :" + event);
		logger.info(AppConstants.REVERSE_ORDER_EVENT,event);
		try {
			OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
			Optional<Orders> order = orderRepository.findById(orderEvent.getOrderRequest().getOrderId());
			
			// order can be delete also
			order.ifPresent(o -> {
				o.setStatus(OrderStatus.FAILED);
				orderRepository.save(o);
			});
			
			
		} catch (Exception e) {
		    e.printStackTrace();
		    logger.error(e.getMessage());
		}
	}
}
