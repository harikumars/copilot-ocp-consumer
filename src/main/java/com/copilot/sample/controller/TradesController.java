package com.copilot.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copilot.sample.configuration.ServiceResponse;
import com.copilot.sample.exception.TransactionException;
import com.copilot.sample.model.Trades;
import com.copilot.sample.service.TradesService;

//annotate the class as rest controller

@RestController
//define the base url
@RequestMapping("/trades")
public class TradesController {
	// autowire the trades service
	@Autowired
	private TradesService tradesService;

	// define the post mapping to handle the request
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	// define the method to handle the request
	public ResponseEntity<ServiceResponse> createTrades(@RequestBody Trades trades) {
		// call the service method to create the trades
		Trades trades1 = null;
		// validate the trades object if tradeid transactiontype amount buyer seller
		// currency are not null
		if (trades.getTradeId() == null || trades.getTransaction() == null || trades.getAmount() == null
				|| trades.getBuyer() == null || trades.getSeller() == null || trades.getCurrency() == null) {
			throw new IllegalArgumentException(
					"TradeId,TransactionType,Amount,Buyer,Seller,Currency should not be null");
		}

		try {
			trades1 = tradesService.create(trades);
		} catch (TransactionException e) {
			throw new RuntimeException(e.getMessage());
		}
		// return the response entity
		return new ResponseEntity<>(new ServiceResponse("success",
				"Trade - " + trades.getTradeId() + "- " + trades.getTransaction() + " completed successfully", trades1),
				HttpStatus.OK);
	}

	// define the get mapping to handle the request
	@GetMapping("/getAll")
	public ResponseEntity<List<Trades>> getAllTrades() {
		// call the service method to get all the trades
		List<Trades> tradesList = tradesService.findAll();
		// return the response entity
		return new ResponseEntity<>(tradesList, HttpStatus.OK);
	}

	// define the get mapping to handle the request
	@GetMapping("/get/{id}")
	// define the method to handle the request
	public ResponseEntity<Trades> getTradesById(@PathVariable("id") String id) {
		// call the service method to get the trades by id
		Trades trades = tradesService.findById(id);
		// return the response entity
		return new ResponseEntity<>(trades, HttpStatus.OK);
	}

	// define the put mapping to handle the request
	@PutMapping("/update/{id}")
	// define the method to handle the request
	public ResponseEntity<Trades> updateTrades(@PathVariable("id") String id, @RequestBody Trades trades) {
		// call the service method to update the trades
		Trades trades1 = tradesService.update(trades);
		// return the response entity
		return new ResponseEntity<>(trades1, HttpStatus.OK);
	}

	// define the delete mapping to handle the request
	@DeleteMapping("/delete/{id}")
	// define the method to handle the request
	public ResponseEntity<?> deleteTrades(@PathVariable("id") String id) {
		// call the service method to delete the trades
		tradesService.delete(id);
		// return the response entity
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// postionreport
	@GetMapping("/positionReport")
	public ResponseEntity<List<Trades>> positionReport() {
		// call the service method to get all the trades
		List<Trades> tradesList = tradesService.positionReport();
		// return the response entity
		return new ResponseEntity<>(tradesList, HttpStatus.OK);
	}
}