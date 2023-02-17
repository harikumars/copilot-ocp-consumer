package com.copilot.sample.service;

import java.util.List;

import com.copilot.sample.exception.CustomerNotFoundException;
import com.copilot.sample.model.Customer;

public interface CustomerService {
	List<Customer> findAll();

	Customer findById(String customerId) throws CustomerNotFoundException;

	Customer create(Customer customer);

	Customer update(Customer customer);

	void delete(Customer customer);

}
