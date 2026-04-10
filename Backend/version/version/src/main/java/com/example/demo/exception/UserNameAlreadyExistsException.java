package com.example.demo.exception;

@SuppressWarnings("serial")
public class UserNameAlreadyExistsException extends RuntimeException {

	
	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	
	public UserNameAlreadyExistsException(String details) {
		super();
		this.message = "User Name Already Exisit";
		this.details = details;
	}
	public UserNameAlreadyExistsException() {
		this.message = "User Name Already Exisit";
		this.details = "None";
		
	}
	
}
