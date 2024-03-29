package com.example.inarayan.exception;

public class ValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	 
	 private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ValidationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
