package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import java.util.List;


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
import com.example.demo.exception.CustomerNotFoudException;
import com.example.demo.service.CustomerService;



@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<List<Customer>>getAllCustomer(){
		List<Customer> customer = customerService.findAllCustomers();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customer){
		Customer newCustomer = customerService.addCustomer(customer.getCustomer());
		return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerDto customer){
		Customer customer1 = customerService.updateCustomer(customer.getCustomer());
		if(customer1 ==null) {
			throw new CustomerNotFoudException("No Customer with UserId "+customer.getCustomer().getUserId());
		}
		
		return new ResponseEntity<>(customer1,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id){
		String msg=customerService.deleteCustomer(id);
		if(msg==null) {
			throw new CustomerNotFoudException("No Customer with Id "+id);
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	
}