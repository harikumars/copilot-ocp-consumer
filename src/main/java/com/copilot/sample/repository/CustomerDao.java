package com.copilot.sample.repository;

import java.util.List;

import com.copilot.sample.model.Customer;

public interface CustomerDao {
	List<Customer> findAll();

	Customer findById(String customerId);

	Customer create(Customer customer);

	Customer update(Customer customer);

	void delete(Customer customer);

}
