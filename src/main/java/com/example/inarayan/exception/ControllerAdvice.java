package com.example.inarayan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> exception(NotFoundException exception) {
		
		return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<Object> exception(ValidationException exception) {
		
		return new ResponseEntity<>(exception.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> exception(Exception exception) {
		
		return new ResponseEntity<>("Internal Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
