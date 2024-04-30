package com.mqConsumer.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mqConsumer.app.entity.StockInfo;

public class JsonUtil {

	public static StockInfo convertToObj(String message) {
		try {
			return new ObjectMapper().readValue(message,StockInfo.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
   public static String convertToString(StockInfo st) {
	   try {
		return new ObjectMapper().writeValueAsString(st);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
   }
}
