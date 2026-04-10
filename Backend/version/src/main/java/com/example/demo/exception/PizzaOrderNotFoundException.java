package com.example.demo.exception;

@SuppressWarnings("serial")
public class PizzaOrderNotFoundException extends RuntimeException {

	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	public PizzaOrderNotFoundException(String details) {
		this.message = "PizzaOrder Not Found";
		this.details = details;
	}
	public PizzaOrderNotFoundException() {
		this.message = "PizzaOrder Not Found";
		this.details = "None";
		
	}

}
