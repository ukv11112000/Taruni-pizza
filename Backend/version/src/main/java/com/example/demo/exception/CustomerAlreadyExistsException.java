package com.example.demo.exception;


@SuppressWarnings("serial")
public class CustomerAlreadyExistsException extends RuntimeException {
	private final  String message;
	private final  String details;
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	
	public CustomerAlreadyExistsException(String details) {
		super();
		this.message = "Customer Already Exisit";
		this.details = details;
	}
	public CustomerAlreadyExistsException() {
		this.message = "Customer Already Exisit";
		this.details = "None";
		
	}
}
