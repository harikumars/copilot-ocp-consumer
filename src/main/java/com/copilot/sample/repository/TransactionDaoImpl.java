package com.copilot.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.copilot.sample.exception.CustomerNotFoundException;
import com.copilot.sample.model.Customer;
import com.copilot.sample.model.Transaction;

//annotate with repository
@Repository

public class TransactionDaoImpl implements TransactionDao {
	// autowired annotation for customer dao
	@Autowired
	private CustomerDao customerDao;

	// aitwre jdbctemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// create a method to handle transaction

	@Override
	// Accept Debit transaction only if there is balance on customer account
	// accept credit transaction only if customer is available
	// return the transaction object
	public Transaction setTransaction(Transaction transaction) throws CustomerNotFoundException {
		Customer customer = customerDao.findById(transaction.getCustomerId());
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found");
		}
		Double balance = getBalance(transaction.getCustomerId());
		if (balance < Double.parseDouble(transaction.getTransactionAmount())) {
			throw new RuntimeException("Insufficient balance");
		}
		// if transaction type is DR then insert the transaction_amount as negative
		// value
		if (transaction.getTransactionType().equals("DR")) {
			transaction.setTransactionAmount("-" + transaction.getTransactionAmount());
		}
		// insert the transaction details into the transaction table
		jdbcTemplate.update("INSERT INTO transaction (customerId, type, amount, date,description) VALUES (?,?,?,?,?)",
				transaction.getCustomerId(), transaction.getTransactionType(), transaction.getTransactionAmount(),
				transaction.getTransactionDate(), transaction.getTransactionDescription());
		return transaction;

	}

	// Accept Debit transaction
	@Override
	public Transaction debit(Transaction transaction) throws CustomerNotFoundException {
		// get the customer object from the customer dao
		Customer customer = customerDao.findById(transaction.getCustomerId());
		// check if the customer is found
		if (customer != null) {
			// create a sql query to get the amount in the transaction object for a customer
			String sql = "SELECT amount FROM transaction WHERE customerId = ? ORDER BY id DESC LIMIT 1";
			// get the amount from the transaction object and surround it with try catch
			// block to catch EmptyResultDataAccessException

			Double amount = jdbcTemplate.queryForObject(sql, new Object[] { transaction.getCustomerId() },
					Double.class);
			// check if the amount is greater than 101

			if (amount >= 101) {
				// subtract the amount from the transaction object
				Double newAmount = amount - Double.valueOf(transaction.getTransactionAmount());

				// create a sql query to insert the transaction customer
				// id,date,type,description and above amount in the repository for a customer
				String sql1 = "INSERT INTO transaction (customerId,date,type,description,amount) VALUES (?,?,?,?,?)";
				// call the update method to insert the transaction customer
				// id,date,type,description and above amount in the repository for a customer
				jdbcTemplate.update(sql1, transaction.getCustomerId(), transaction.getTransactionDate(), "DR",
						transaction.getTransactionDescription(), newAmount);
				// return the transaction object after the insert
				return transaction;
			} else {
				// throw an exception if the amount is less than 0
				throw new CustomerNotFoundException("No sufficient balance");
			}

		} else {
			// throw an exception if the customer is not found
			throw new CustomerNotFoundException("Customer not found");
		}
	}

	@Override
	public Transaction credit(Transaction transaction) throws CustomerNotFoundException {
		System.out.println("transaction" + transaction.getCustomerId());
		System.out.println("transaction" + transaction.getTransactionAmount());
		// get the customer object from the customer dao
		Customer customer = customerDao.findById(transaction.getCustomerId());
		// check if the customer is found
		if (customer != null) {
			// create a sql query to get the amount in the transaction object for a customer
			String sql = "SELECT amount FROM transaction WHERE customerId = ? ORDER BY id DESC LIMIT 1";
			// get the amount from the transaction object using the transactionrowmapper

			// surround with try catch block
			try {
				Double amount = jdbcTemplate.queryForObject(sql, new Object[] { transaction.getCustomerId() },
						Double.class);
				// check if the amount is greater than 0
				if (amount >= 0) {
					// add the amount from the transaction object
					Double newAmount = amount + Double.valueOf(transaction.getTransactionAmount());

					// create a sql query to insert the transaction customer
					// id,date,type,description and above amount in the repository for a customer
					String sql1 = "INSERT INTO transaction (customerId,date,type,description,amount) VALUES (?,?,?,?,?)";
					// call the update method to insert the transaction customer
					// id,date,type,description and above amount in the repository for a customer
					jdbcTemplate.update(sql1, transaction.getCustomerId(), transaction.getTransactionDate(), "CR",
							transaction.getTransactionDescription(), newAmount);
					// return the transaction object after the insert
					return transaction;
				}
			} catch (EmptyResultDataAccessException e) {
				// create a sql query to insert the transaction customer
				// id,date,type,description and above amount in the repository for a customer
				String sql1 = "INSERT INTO transaction (customerId,date,type,description,amount) VALUES (?,?,?,?,?)";
				// call the update method to insert the transaction customer
				// id,date,type,description and above amount in the repository for a customer
				jdbcTemplate.update(sql1, transaction.getCustomerId(), transaction.getTransactionDate(), "CR",
						transaction.getTransactionDescription(), transaction.getTransactionAmount());
				// return the transaction object after the insert
				return transaction;
			}

		} else {
			// throw an exception if the customer is not found
			throw new CustomerNotFoundException("Customer not found");
		}
		return transaction;
	}

	@Override
	public Double getBalance(String customerId) {
		// get the balance from the transaction object with the latest transaction id
		String sql = "SELECT amount FROM transaction WHERE customerId = ? ORDER BY id DESC LIMIT 1";
		// get the balance from the transaction object
		Double amount = jdbcTemplate.queryForObject(sql, new Object[] { customerId }, Double.class);
//handle the exception
		try {
			// return the balance
			return amount;
		} catch (EmptyResultDataAccessException e) {
			// return 0 if the balance is not found
			return 0.0;
		}
	}

	// create a method to print all the transactions for a customer
	@Override
	public List<Transaction> getAllTransactions(String customerId) {
		// create a sql query to get all the transactions for a customer
		String sql = "SELECT * FROM transaction WHERE customerId = ?";
		// get all the transactions for a customer
		List<Transaction> transactions = jdbcTemplate.query(sql, new Object[] { customerId },
				new TransactionRowMapper());
		// return the list of transactions
		return transactions;
	}
}
