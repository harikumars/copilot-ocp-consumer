package com.copilot.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copilot.sample.exception.TransactionException;
import com.copilot.sample.model.Trades;
import com.copilot.sample.repository.TradesDao;

//Add service configuration to TradesService class
@Service
public class TradesService {
	// Add autowired annotation to TradesDao
	@Autowired
	private TradesDao tradesDao;

	// Add custom query to find all trades
	public List<Trades> findAll() {
		return tradesDao.findAll();
	}

	// Add custom query to find trades by id
	public Trades findById(String tradesId) {
		return tradesDao.findById(tradesId);
	}

	// Add custom query to create a new trade
	public Trades create(Trades trades) throws TransactionException {
		return tradesDao.create(trades);
	}

	// Add custom query to update a trade
	public Trades update(Trades trades) {
		return tradesDao.update(trades);
	}

	// Add custom query to delete a trade
	public void delete(String trades) {
		tradesDao.delete(trades);
	}

	public List<Trades> positionReport() {
		return tradesDao.positionReport();
	}
}