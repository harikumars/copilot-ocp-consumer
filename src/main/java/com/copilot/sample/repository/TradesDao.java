package com.copilot.sample.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.copilot.sample.exception.TransactionException;
import com.copilot.sample.model.Trades;

//Add repository configuration to TradesDao class
@Repository
public interface TradesDao {
	// Add custom query to find all trades
	List<Trades> findAll();

	// Add custom query to find trades by id
	Trades findById(String tradesId);

	// Add custom query to create a new trade
	Trades create(Trades trades) throws TransactionException;

	// Add custom query to update a trade
	Trades update(Trades trades);

	// Add custom query to delete a trade
	void delete(String trades);

	List<Trades> positionReport();
}