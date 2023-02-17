package com.copilot.sample.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//add global exception handler to handle all exceptions in this project
import com.copilot.sample.exception.CustomerNotFoundException;
import com.copilot.sample.exception.TransactionException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// handle customer not found exception
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	// handle transaction exception
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<String> handleTransactionException(TransactionException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// handle illegal argument exception
	@ExceptionHandler(IllegalArgumentException.class)
	// returns a response entity as ServiceResponse object with the exception
	// message and a bad request status
	public ResponseEntity<ServiceResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		return new ResponseEntity<>(new ServiceResponse("error", e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	// handle runtime exception
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServiceResponse> handleRuntimeException(RuntimeException e) {
		return new ResponseEntity<>(new ServiceResponse("error", e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}

	// handle all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceResponse> handleException(Exception e) {
		return new ResponseEntity<>(new ServiceResponse("error", e.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// handle data access exception
	@ExceptionHandler(org.springframework.dao.DataAccessException.class)
	public ResponseEntity<String> handleDataAccessException(org.springframework.dao.DataAccessException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
