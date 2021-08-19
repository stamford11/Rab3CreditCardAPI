package com.creditcard.service;

import java.util.List;

import com.creditcard.dto.CreditCardDTO;

public interface CreditCardService {

	void persist(CreditCardDTO creditCardDTO);

	List<CreditCardDTO> findAllCC();

	CreditCardDTO findCCById(int ccid);

	void deleteCC(int ccid);

	void update(int ccid, CreditCardDTO cardDTO);

}
