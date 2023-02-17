package com.copilot.sample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.copilot.sample.controller.TradesController;
import com.copilot.sample.model.Trades;

@Component
public class MQConsumer {
	
    @Autowired
    private TradesController tradesController;

    @JmsListener(destination = "testQ")
    public void receiveMessage(String trades) {
    	System.out.println("Received <" + trades.toString() + ">");
        //tradesController.createTrades(trades);
    }
}
