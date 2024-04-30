package com.mqConsumer.app.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mqConsumer.app.entity.StockInfo;
import com.mqConsumer.app.repo.StockRepository;
import com.mqConsumer.app.util.JsonUtil;

@Component
public class MessageStore {

	@Autowired
	private StockRepository stockRepo;
	
	public StockInfo add(String message) {
		StockInfo stock = JsonUtil.convertToObj(message);
		return this.stockRepo.save(stock);
	}
	
	public List<StockInfo> getAllStockInfo(){
		return this.stockRepo.findAll();
	}
	
	
}
