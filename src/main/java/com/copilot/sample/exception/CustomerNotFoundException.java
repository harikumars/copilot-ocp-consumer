package com.copilot.sample.exception;

public class CustomerNotFoundException extends Throwable {
	public CustomerNotFoundException(String exMsg) {
		super(exMsg);
	}
}
