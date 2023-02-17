package com.copilot.sample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.copilot.sample.model.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {
	// create a customer row mapper to map the values from the result set to the
	// customer object
	@Override
	public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(resultSet.getString("id"));
		customer.setCustomerName(resultSet.getString("name"));
		customer.setCustomerEmail(resultSet.getString("email"));
		customer.setLei(resultSet.getString("LEI"));
		customer.setSwiftBic(resultSet.getString("SWIFTBIC"));

		customer.setCici(resultSet.getString("CICI"));
		customer.setDtcc(resultSet.getString("DTCC"));
		customer.setAvox(resultSet.getString("AVOX"));
		return customer;
	}

}
