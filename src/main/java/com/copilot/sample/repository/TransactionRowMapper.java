package com.copilot.sample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//implement rowmapper for transaction
import com.copilot.sample.model.Transaction;

//add row mapper interface
public class TransactionRowMapper implements RowMapper<Transaction> {
	// map the values from the result set to the transaction object
	@Override
	public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(resultSet.getString("id"));
		transaction.setTransactionType(resultSet.getString("type"));
		transaction.setTransactionAmount(resultSet.getString("amount"));
		transaction.setTransactionDate(resultSet.getString("date"));
		transaction.setCustomerId(resultSet.getString("customer_id"));
		return transaction;
	}
}
