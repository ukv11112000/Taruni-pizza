package com.example.demo.exception;

@SuppressWarnings("serial")
public class PizzaAlreadyExistsException extends RuntimeException {

	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	public PizzaAlreadyExistsException(String details) {
		this.message = "Pizza Already Exists";
		this.details = details;
	}
	public PizzaAlreadyExistsException() {
		this.message = "Pizza Already Exists";
		this.details = "None";
		
	}

}
