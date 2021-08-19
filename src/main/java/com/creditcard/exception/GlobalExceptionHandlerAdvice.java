package com.creditcard.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // ->> This is global exception handler
@RestController
// @RestController ->>to generate error as json data
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CreditCardException.class)
	public final ResponseEntity<ErrorDetails> handleCreditCardException(CreditCardException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); // 400
	}

	@ExceptionHandler(BankServiceException.class)
	public final ResponseEntity<ErrorDetails> handleBankServiceException(BankServiceException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Throwable.class)
	public final ResponseEntity<ErrorDetails> handleException(Throwable ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}