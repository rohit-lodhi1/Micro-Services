package com.chat.app.util;

import com.chat.app.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {

	public static Message convertToObj(String message) {
		try {
			return new ObjectMapper().readValue(message,Message.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
   public static String convertToString(Message st) {
	   try {
		return new ObjectMapper().writeValueAsString(st);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return null;
   }
}
