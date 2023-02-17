package com.copilot.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//add customer service class
import com.copilot.sample.exception.CustomerNotFoundException;
import com.copilot.sample.model.Customer;
import com.copilot.sample.repository.CustomerDao;
import com.copilot.sample.service.CustomerService;

@Service

public class CustomerImpl implements CustomerService {

	// Autowire customerDao
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	public CustomerImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Customer findById(String customerId) throws CustomerNotFoundException {
		// call the customerDao findById method
		Customer customer = customerDao.findById(customerId);
		// check if the customer is null
		if (customer == null) {
			// throw customer not found exception
			throw new CustomerNotFoundException("Customer not found");
		}
		// return the customer object
		return customer;
	}

	@Override
	public Customer create(Customer customer) {
		// call the customerDao create method
		return customerDao.create(customer);
	}

	@Override
	public Customer update(Customer customer) {
		// call the customerDao update method
		return customerDao.update(customer);
	}

	@Override
	public void delete(Customer customer) {
		// call the customerDao delete method
		customerDao.delete(customer);
	}
}