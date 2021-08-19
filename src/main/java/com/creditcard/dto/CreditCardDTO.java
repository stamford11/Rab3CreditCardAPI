package com.creditcard.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreditCardDTO {

	private int ccid;
	private String name;
	private String ccno;
	private int cvv;
	private String exp;
	private int balance;
	private Timestamp doe;
}
