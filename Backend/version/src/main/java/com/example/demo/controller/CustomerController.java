package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDto;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.exception.CustomerNotFoudException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final CustomerService customerService;
	private final CustomerRepository customerRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public CustomerController(CustomerService customerService,CustomerRepository customerRepository) {
		this.customerService=customerService;
		this.customerRepository= customerRepository;
	}
	
	@GetMapping("/all")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<List<Customer>>getAllCustomer(){
		logger.info("Hitted the /customer/all endpoint");
		List<Customer> customer = customerService.findAllCustomers();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@PostMapping(value="/add",consumes={"application/json"})
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customer){
		logger.info("Hitted the /customer/add endpoint");
		Customer newCustomer = customerService.addCustomer(customer.getCustomer());
		return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value="/update",consumes= {"application/json"})
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerDto customer){
		logger.info("Hitted the /customer/update endpoint");
		Customer customer1 = customerService.updateCustomer(customer.getCustomer());
		if(customer1 ==null) {
			throw new CustomerNotFoudException("No Customer with UserId "+customer.getCustomer().getUserId());
		}
		
		return new ResponseEntity<>(customer1,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id){
		logger.info("Hitted the /customer/delete endpoint");
		String msg=customerService.deleteCustomer(id);
		if(msg==null) {
			throw new CustomerNotFoudException("No Customer with Id "+id);
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/currentCustomer/{username}")
	public ResponseEntity<Customer>getCustomer(@PathVariable("username") String username){
		logger.info("Hitted the /customer/currentCustomer endpoint");
		Customer customer = customerService.getCurrentCustomer(username);
		if(customer==null) {
			throw new CustomerNotFoudException("No Customer with username "+username);
		}
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	
	
}