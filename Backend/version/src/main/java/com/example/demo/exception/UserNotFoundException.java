package com.example.demo.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	
	private final String message;
	private final String details;
	@Override
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}

	public UserNotFoundException(String details) {
		super();
		this.message = "User Not Found";
		this.details = details;
	}
	public UserNotFoundException() {
		this.message = "User Not Found";
		this.details = "None";
		
	}
	@Override
	public String toString() {
		return "UserNotFoundException [message=" + message + ", details=" + details + "]";
	}
	

	
	

	
}
