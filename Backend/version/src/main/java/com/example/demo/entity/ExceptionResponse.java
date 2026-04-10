package com.example.demo.entity;

import java.sql.Timestamp;
import java.time.Instant;


public class ExceptionResponse {

    private String message;
    private String details;
    private  Timestamp time;
    public ExceptionResponse() {
    	this.time= Timestamp.from(Instant.now());
    }
    public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDateTime() {
        return details;
    }
    public void setDetails(String dateTime) {
        this.details = dateTime;
    }
    
}