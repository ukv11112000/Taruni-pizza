package com.example.demo.service;


import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerAlreadyExistsException;
import com.example.demo.repository.CustomerRepository;



@Service
@Transactional
public class CustomerService {
	private final CustomerRepository customerRepository;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepo) {
		this.customerRepository = customerRepo;
		
	}
	public boolean isvalid(Customer customer) {
		if(!(customerRepository.findByEmail(customer.getEmail()).isEmpty())) {
			logger.error("Customer already exists");
			throw new CustomerAlreadyExistsException("Customer Already exists with Email "+customer.getEmail());
		}
		if(!(customerRepository.findByPhone(customer.getPhone()).isEmpty())) {
			logger.error("Customer already exists");
			throw new CustomerAlreadyExistsException("Customer Already exists with Phone "+customer.getPhone());
		}
		
		
		return true;
	}
	public Customer addCustomer(Customer customer) {
		String pwd = customer.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		customer.setPassword(encryptPwd);
		customer.setRoles("ROLE_USER");
		return customerRepository.save(customer);
	}
	
	public List<Customer> findAllCustomers(){
		return customerRepository.findAll();
	}
	
	public String deleteCustomer(int id){
		Customer customer= customerRepository.findById(id).orElse(null);
		if(customer==null) {
			logger.error("Customer not found");
			return null;
		}
		customerRepository.delete(customer);
		return "Customer Deleted";
	}
		
	public Customer updateCustomer(Customer customer) {
		Customer newcustomer=customerRepository.findById(customer.getUserId()).orElse(null);
		if(newcustomer==null) {
			logger.error("Customer data invalid");
			return null;
		}
		return customerRepository.save(customer);
	}
	
	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public Customer getCurrentCustomer(String username) {
		Customer customer=customerRepository.findByUsername(username).orElse(null);
		if(customer==null) {
			logger.error("Customer not found");
			return null;
		}
		
		return customer;
	}

	
	
	
}