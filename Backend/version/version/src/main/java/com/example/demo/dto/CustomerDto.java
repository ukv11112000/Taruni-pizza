package com.example.demo.dto;

import com.example.demo.entity.Customer;

public class CustomerDto {

	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerDto(Customer customer) {
		super();
		this.customer = customer;
	}

	public CustomerDto() {
		super();
		
	}
	
}
