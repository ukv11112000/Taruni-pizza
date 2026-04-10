package com.example.demo.exception;

@SuppressWarnings("serial")
public class CustomerNotFoudException extends RuntimeException {

	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	public CustomerNotFoudException(String details) {
		this.message = "Customer Not Found";
		this.details = details;
	}
	public CustomerNotFoudException() {
		this.message = "Customer Not Found";
		this.details = "None";
		
	}

}
