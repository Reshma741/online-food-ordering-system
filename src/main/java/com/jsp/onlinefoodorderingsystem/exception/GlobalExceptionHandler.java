package com.jsp.onlinefoodorderingsystem.exception;


import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.onlinefoodorderingsystem.responseStructure.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementException(NoSuchElementException exception){
		ResponseStructure<String> response= new ResponseStructure<String>(HttpStatus.NOT_FOUND.value(), "exception created and handled", exception.getMessage()); 
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(PaymentFailedException.class)
	public ResponseEntity<ResponseStructure<String>> paymentFailedException(PaymentFailedException exception){
		ResponseStructure<String> apiResponse = new ResponseStructure<>(HttpStatus.NOT_ACCEPTABLE.value(),"Exception handled", exception.getMessage());
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_ACCEPTABLE);
	}

}
