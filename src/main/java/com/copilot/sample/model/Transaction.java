package com.copilot.sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id
	private String transactionId;
	private String transactionAmount;
	private String transactionDate;
	private String transactionType;
	private String transactionStatus;
	private String transactionDescription;
//add customer id string
	private String customerId;
}
