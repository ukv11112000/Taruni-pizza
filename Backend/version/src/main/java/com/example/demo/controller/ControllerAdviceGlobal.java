package com.example.demo.controller;



import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.entity.ExceptionResponse;
import com.example.demo.exception.CoupanNotFoundException;
import com.example.demo.exception.CustomerAlreadyExistsException;
import com.example.demo.exception.CustomerNotFoudException;
import com.example.demo.exception.PizzaAlreadyExistsException;
import com.example.demo.exception.PizzaInvalidException;
import com.example.demo.exception.PizzaNotFoundException;
import com.example.demo.exception.PizzaOrderInvalidException;
import com.example.demo.exception.PizzaOrderNotFoundException;
import com.example.demo.exception.SuperAdminCannotBeRemovedException;
import com.example.demo.exception.UserNameAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerAdviceGlobal {

	@ExceptionHandler(UserNotFoundException .class)
	public ResponseEntity<Object> userNotFound( UserNotFoundException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomerNotFoudException .class)
	public ResponseEntity<Object> customerNotFound( CustomerNotFoudException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PizzaOrderNotFoundException .class)
	public ResponseEntity<Object> pizzaOrderNotFound( PizzaOrderNotFoundException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PizzaNotFoundException .class)
	public ResponseEntity<Object> pizzaNotFound( PizzaNotFoundException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CoupanNotFoundException .class)
	public ResponseEntity<Object> coupanNotFound( CoupanNotFoundException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNameAlreadyExistsException .class)
	public ResponseEntity<Object> userAlreadyExist( UserNameAlreadyExistsException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(SuperAdminCannotBeRemovedException .class)
	public ResponseEntity<Object> superAdminCannotBeRemovedException( SuperAdminCannotBeRemovedException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(PizzaAlreadyExistsException .class)
	public ResponseEntity<Object> pizzaAlreadyExist( PizzaAlreadyExistsException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(CustomerAlreadyExistsException .class)
	public ResponseEntity<Object> customerAlreadyExist( CustomerAlreadyExistsException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(PizzaInvalidException .class)
	public ResponseEntity<Object> pizzaInvalidException( PizzaInvalidException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(PizzaOrderInvalidException .class)
	public ResponseEntity<Object> pizzaOrderInvalidException( PizzaOrderInvalidException e){
		ExceptionResponse response = new ExceptionResponse();
        response.setDetails(e.getDetails());
        response.setMessage(e.getMessage());
        response.setTime(Timestamp.from(Instant.now()));
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
	}
	
}
