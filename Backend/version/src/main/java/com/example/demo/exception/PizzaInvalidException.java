package com.example.demo.exception;

@SuppressWarnings("serial")
public class PizzaInvalidException extends RuntimeException{
	private final  String message;
	private final  String details;
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}

	public PizzaInvalidException() {
		
		this.message = "Invalid Pizza Order";
		this.details = "None";
	}
	

	public PizzaInvalidException( String details) {
		
		this.message = "Invalid Pizza Order";
		this.details = details;
	}
	
}
