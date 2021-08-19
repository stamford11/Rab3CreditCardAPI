package com.creditcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.dto.AppVO;
import com.creditcard.dto.CreditCardDTO;
import com.creditcard.exception.CreditCardException;
import com.creditcard.service.CreditCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v4")

//to display in swagger ui
@Api(value = "CreditCardController", description = "REST APIs related to CreditCard Entity!!!!")
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;

	@PostMapping("/creditcard")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AppVO postCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
		creditCardService.persist(creditCardDTO);
		AppVO appVO = new AppVO();
		appVO.setCode(200);
		appVO.setMessage("Credit Card has been sucssfully saved in database!");
		return appVO;

	}

	@ApiOperation(value = "View a list of available CreditCards", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/creditcard")
	@ResponseStatus(code = HttpStatus.OK)
	public List<CreditCardDTO> getCreditCard() {
		return creditCardService.findAllCC();

	}

	@GetMapping("/creditcard/{cid}")
	@ResponseStatus(code = HttpStatus.OK)
	public CreditCardDTO getCreditCardById(@PathVariable("cid") int ccid) {
		if (ccid < 1) {
			throw new CreditCardException("Error!! Cannot retrive ID: " + ccid + ". ID cannot be negative");
		}
		return creditCardService.findCCById(ccid);
	}

	@DeleteMapping("/creditcard/{cid}")
	@ResponseStatus(code = HttpStatus.OK)
	public AppVO deleteById(@PathVariable("cid") int ccid) {
		if (ccid < 1) {
			throw new CreditCardException("Error!! Cannot delete ID: " + ccid + ". ID cannot be negative");
		}
		creditCardService.deleteCC(ccid);
		AppVO appVO = new AppVO();
		appVO.setCode(200);
		appVO.setMessage("Record with ID " + ccid + " deleted successfully");

		return appVO;
	}

	@PutMapping("/creditcard/{cid}")
	@ResponseStatus(code = HttpStatus.OK)
	public AppVO putCreditCard(@PathVariable("cid") int ccid, @RequestBody CreditCardDTO cardDTO) {
		if (ccid < 1) {
			throw new CreditCardException("Error!! Cannot retrive ID: " + ccid + ". ID cannot be negative");
		}
		creditCardService.update(ccid, cardDTO);
		AppVO appVO = new AppVO();
		appVO.setCode(200);
		appVO.setMessage("Record with ID " + ccid + " updated successfully");

		return appVO;
	}
}
