package com.copilot.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.copilot.sample.configuration.ServiceResponse;
import com.copilot.sample.exception.CustomerNotFoundException;
import com.copilot.sample.model.Transaction;
import com.copilot.sample.repository.TransactionDao;

//annotate rest controller
@RestController
public class TransactionController {
	// autowired transactiondao
	@Autowired
	private TransactionDao transactionDao;

	// create a method to handle transactions with post mapping and request body
	// annotation
	@PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> setTransaction(@RequestBody Transaction transaction)
			throws CustomerNotFoundException {
		// call the set transaction method from the transaction dao
		transactionDao.setTransaction(transaction);
		// return the response entity with the service response
		return ResponseEntity.ok(new ServiceResponse("success", "Transaction successfull", transaction));
	}

	@PostMapping(value = "/credit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ServiceResponse> credit(@RequestBody Transaction transaction) {
		try {
			Transaction transaction1 = transactionDao.credit(transaction);
		} catch (CustomerNotFoundException e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity
				.ok(new ServiceResponse("success", "Transaction successful and amount credited", transaction));
	}

	// debit post mapping method to create a new transaction only if the customer is
	// listed in the repository
	@PostMapping(value = "/debit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ServiceResponse> debit(@RequestBody Transaction transaction) {
		try {
			Transaction transaction1 = transactionDao.debit(transaction);
		} catch (CustomerNotFoundException e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity
				.ok(new ServiceResponse("success", "Transaction successful and amount debited", transaction));
	}

	// get balance post mapping method to get the current balance on the customer
	// account
	@GetMapping("/transaction/getBalance/{customerId}")

	public Double getBalance(@PathVariable("customerId") String customerId) {
		return transactionDao.getBalance(customerId);
	}

}
