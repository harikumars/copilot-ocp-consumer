package com.copilot.sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.ToString;

// fields are transaction, tradeid,buyer,seller,amount,currency,tradeDate,status
@ToString
@Entity
public class Trades {
	private String transaction;

	@Id
	private String tradeId;
	private String buyer;
	private String seller;
	private String amount;
	private String currency;
	private String tradeDate;
	private String status;

	public Trades() {
	}

	public Trades(String transaction, String tradeId, String buyer, String seller, String amount, String currency,
			String tradeDate, String status) {
		this.transaction = transaction;
		this.tradeId = tradeId;
		this.buyer = buyer;
		this.seller = seller;
		this.amount = amount;
		this.currency = currency;
		this.tradeDate = tradeDate;
		this.status = status;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}