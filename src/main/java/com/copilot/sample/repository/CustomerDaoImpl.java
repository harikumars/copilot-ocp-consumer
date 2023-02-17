package com.copilot.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.copilot.sample.model.Customer;

//Add repository configuration to CustomerDaoImpl class
@Repository
public class CustomerDaoImpl implements CustomerDao {

	// Autowire jdbctemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Customer> findAll() {
		String sql = "SELECT * FROM customer";
		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}

	@Override
	public Customer findById(String customerId) {
		// create a sql query to find a customer by id
		String sql = "SELECT * FROM customer WHERE id = ?";
		// return the queryForObject for customer
		return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), new Object[] { customerId });
	}

	@Override
	public Customer create(Customer customer) {
		String sql = "INSERT INTO customer (name,  email ,LEI,SWIFTBIC,CICI,DTCC,AVOX) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, customer.getCustomerName(), customer.getCustomerEmail(), customer.getLei(),
				customer.getSwiftBic(), customer.getCici(), customer.getDtcc(), customer.getAvox());

		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		// create a sql query to update a customer by id using the customer object
		String sql = "UPDATE customer SET name = ?, email = ?, LEI = ?, SWIFTBIC = ?, CICI = ?, DTCC = ?, AVOX = ? WHERE id = ?";

		// call the update method to update the customer using the customer object with
		// the rowmapper object
		jdbcTemplate.update(sql, customer.getCustomerName(), customer.getCustomerEmail(), customer.getLei(),
				customer.getSwiftBic(), customer.getCici(), customer.getDtcc(), customer.getAvox(),
				customer.getCustomerId());

		// return the customer object after the update
		return customer;
	}

	@Override
	public void delete(Customer customer) {
		// create a sql query to delete a customer using the customer object

		String sql = "DELETE FROM customer WHERE id = ?";
		// call the update method to delete the customer using the customer object
		jdbcTemplate.update(sql, customer.getCustomerId());
	}

}
