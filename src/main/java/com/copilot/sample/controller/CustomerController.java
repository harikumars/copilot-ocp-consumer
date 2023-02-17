package com.copilot.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.copilot.sample.configuration.ServiceResponse;
import com.copilot.sample.exception.CustomerNotFoundException;
import com.copilot.sample.model.Customer;
import com.copilot.sample.service.CustomerService;

//annotate rest controller
@RestController
public class CustomerController {

	// autowire customer service class
	@Autowired
	private CustomerService customerService;

	// annotation to define get method for all customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.findAll();
	}

	// annotation to define postmapping and body parameter
	@PostMapping(value = "/createCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ServiceResponse> createCustomer(@RequestBody Customer customer) {
		// validate the customer phone and email with the list of customer object in the
		// repository
		// add validation if customer name and email is not null
		if (customer.getCustomerName() == null || customer.getCustomerName().equals("")
				|| customer.getCustomerEmail() == null || customer.getCustomerEmail().equals("")) {
			throw new IllegalArgumentException("Customer Name should not be null");
		}
		for (Customer c : customerService.findAll()) {
			if (c.getCustomerName().equals(customer.getCustomerName())) {
				throw new IllegalArgumentException("Customer Name already exists");
			}
		}
		// call the create method to create a new customer
		Customer cust = customerService.create(customer);
		// return the response entity after creating the customer
		return ResponseEntity.ok(new ServiceResponse("success", "Customer added", cust));

	}

	// annotation to define put method and body parameter
	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.update(customer);
	}

	// annotation to define delete method for a particular id using path variable
	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable String id) throws CustomerNotFoundException {
		Customer customer = customerService.findById(id);
		customerService.delete(customer);
	}

	// annotation to define get method for a particular customerId
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable String customerId) throws CustomerNotFoundException {
		return customerService.findById(customerId);
	}

}
