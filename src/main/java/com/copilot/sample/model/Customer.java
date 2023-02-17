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
public class Customer {
	@Id
	private String customerId;
	private String customerName;
	private String customerEmail;
	private String lei;
	private String swiftBic;
	private String cici;
//add zipcode and city
	private String dtcc;
	private String avox;

}
