package com.creditcard.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcard.Repository.CreditCardRepository;
import com.creditcard.dto.CreditCardDTO;
import com.creditcard.entity.CreditCardEntity;
import com.creditcard.exception.BankServiceException;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Override
	public void persist(CreditCardDTO creditCardDTO) {
		CreditCardEntity cardEntity = new CreditCardEntity();
		BeanUtils.copyProperties(creditCardDTO, cardEntity);
		// Creating a timestamp
		Timestamp timestamp = new Timestamp(new Date().getTime());
		cardEntity.setDoe(timestamp);
		creditCardRepository.save(cardEntity);
	}

	@Override
	public List<CreditCardDTO> findAllCC() {
		List<CreditCardDTO> cardDTOs = new ArrayList<CreditCardDTO>();
		List<CreditCardEntity> cardEntities = creditCardRepository.findAll();
		for (CreditCardEntity creditCardEntity : cardEntities) {
			CreditCardDTO cardDTO = new CreditCardDTO();
			BeanUtils.copyProperties(creditCardEntity, cardDTO);
			cardDTOs.add(cardDTO);
		}
		return cardDTOs;
	}

	@Override
	public CreditCardDTO findCCById(int ccid) {
		CreditCardDTO cardDTO = new CreditCardDTO();
		Optional<CreditCardEntity> cardEntity = creditCardRepository.findById(ccid);
		if (cardEntity.isEmpty()) {
			throw new BankServiceException("Error!!Cannot find the ID: " + ccid + " in the database");
		}
		BeanUtils.copyProperties(cardEntity, cardDTO);

		return cardDTO;
	}

	@Override
	public void deleteCC(int ccid) {
		Optional<CreditCardEntity> cardEntity = creditCardRepository.findById(ccid);
		if (cardEntity.isEmpty()) {
			throw new BankServiceException("Error!!Cannot find the ID: " + ccid + " in the database");
		}
		creditCardRepository.deleteById(ccid);

	}

	@Override
	public void update(int ccid, CreditCardDTO cardDTO) {
		if (creditCardRepository.findById(ccid).isEmpty()) {
			throw new BankServiceException("Error!!Cannot find the ID: " + ccid + " in the database");
		} else {
			CreditCardEntity cardEntity = new CreditCardEntity();
			cardEntity.setBalance(cardDTO.getBalance());
			cardEntity.setCcno(cardDTO.getCcno());
			cardEntity.setCvv(cardDTO.getCvv());
			Timestamp timestamp = new Timestamp(new Date().getTime());
			cardEntity.setDoe(timestamp);
			cardEntity.setExp(cardDTO.getExp());
			cardEntity.setName(cardDTO.getName());
			creditCardRepository.save(cardEntity);
		}
	}

}
