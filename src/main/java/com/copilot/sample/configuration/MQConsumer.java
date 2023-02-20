package com.copilot.sample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.copilot.sample.controller.TradesController;
import com.copilot.sample.model.Trades;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MQConsumer {

	@Autowired
	private TradesController tradesController;
	
	
	
	@JmsListener(destination = "testQ")
	public void receiveMessage(String trades) {
		System.out.println("Received <" + trades.toString() + ">");
		
		ObjectMapper objectMapper = new ObjectMapper();
		Trades tradesObject;
		try {
			tradesObject = objectMapper.readValue(trades, Trades.class);
			System.out.println(tradesObject.toString());
			
			tradesController.createTrades(tradesObject);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}




