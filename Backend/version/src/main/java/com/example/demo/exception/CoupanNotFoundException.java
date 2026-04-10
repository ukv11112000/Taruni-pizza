package com.example.demo.exception;

@SuppressWarnings("serial")
public class CoupanNotFoundException extends RuntimeException{

	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	public CoupanNotFoundException(String details) {
		this.message = "Coupan Not Found";
		this.details = details;
	}
	public CoupanNotFoundException() {
		this.message = "Coupan Not Found";
		this.details = "None";
		
	}

}
