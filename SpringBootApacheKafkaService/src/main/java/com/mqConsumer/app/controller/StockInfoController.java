package com.mqConsumer.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.mqConsumer.app.db.MessageStore;
import com.mqConsumer.app.entity.StockInfo;
import com.mqConsumer.app.service.ProducerService;
import com.mqConsumer.app.util.JsonUtil;

@RestController
@RequestMapping("/api/stock/info")
public class StockInfoController {

  @Autowired
  private ProducerService producerService;
  
  @Autowired
  private MessageStore mStore;
  
  @GetMapping("/send")
  public String readMessage(
		  @RequestParam String code,
		  @RequestParam Double cost
		  ){
	 StockInfo si = new StockInfo();
	 si.setStkCode(code);
	 si.setCost(cost);
	 String message = JsonUtil.convertToString(si);
	 this.producerService.sendMessage(message);
	 return "sent";
  }
  
  
	@GetMapping("/all")
	public List<StockInfo> getAll(){
	return this.mStore.getAllStockInfo();
	}
  
}
