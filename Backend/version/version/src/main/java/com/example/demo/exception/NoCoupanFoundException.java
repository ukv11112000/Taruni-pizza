package com.example.demo.exception;

@SuppressWarnings("serial")
public class NoCoupanFoundException extends RuntimeException {

	private final  String message;
	private final  String details;
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	public NoCoupanFoundException() {
		this.message = "No Coupans in the DataBase";
		this.details = "None";
	}
	
}
