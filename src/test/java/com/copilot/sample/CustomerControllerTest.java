package com.copilot.sample;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

//create a junit for the customer controller
import com.copilot.sample.controller.CustomerController;
import com.copilot.sample.model.Customer;
import com.copilot.sample.service.CustomerService;

//Add spring runner
@RunWith(SpringRunner.class)
public class CustomerControllerTest {
	// Add mockito annotations
	@Mock
	private CustomerService customerService;
	// Add inject mocks
	@InjectMocks
	private CustomerController customerController;

	// Add before method
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	// Add test method to test the create customer
	@Test

	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId("1");
		customer.setCustomerName("John");
		customer.setCustomerEmail("John@dt.com");
		when(customerService.create(customer)).thenReturn(customer);
		ResponseEntity responseEntity = customerController.createCustomer(customer);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
