package com.example.demo.exception;

@SuppressWarnings("serial")
public class PizzaNotFoundException extends RuntimeException{

	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	public PizzaNotFoundException(String details) {
		this.message = "Pizza Not Found";
		this.details = details;
	}
	public PizzaNotFoundException() {
		this.message = "Pizza Not Found";
		this.details = "None";
		
	}

}
