package com.example.demo.exception;

@SuppressWarnings("serial")
public class SuperAdminCannotBeRemovedException extends RuntimeException {

	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	public SuperAdminCannotBeRemovedException(String details) {
		this.message = "Super Admin Cannot Be Removed";
		this.details = details;
	}
	public SuperAdminCannotBeRemovedException() {
		this.message = "Super Admin Cannot Be Removed";
		this.details = "None";
		
	}

}
