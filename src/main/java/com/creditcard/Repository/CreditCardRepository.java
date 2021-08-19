package com.creditcard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.entity.CreditCardEntity;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Integer> {

}
