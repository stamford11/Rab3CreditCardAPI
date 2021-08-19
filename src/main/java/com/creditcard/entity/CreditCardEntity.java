package com.creditcard.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author supen
 *
 */
@Getter
@Setter
@ToString
@Entity


public class CreditCardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ccid;
	private String name;
	private String ccno;
	private int cvv;
	private String exp;
	private int balance;
	private Timestamp doe;
	

}
