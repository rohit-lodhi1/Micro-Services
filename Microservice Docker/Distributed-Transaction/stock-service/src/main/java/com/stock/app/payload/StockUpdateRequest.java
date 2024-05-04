package com.stock.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateRequest {

	private String item;
	
	private int quantity;
	
	private Double amount;

	private String paymentMode;
	
	private Long orderId;
	
	private String address;
}
