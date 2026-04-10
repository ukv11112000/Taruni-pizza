package com.example.demo.entity;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Customer extends User{
	
	private String name;
	private String email;
	private String phone;
	private String address;

	public Customer() {}

   
	
	public String getName() {
		return  name;
	}

	public Customer(int userId, String username, String password, String roles, List<Order> order,
			String name, String email, String phone, String address) {
		super(userId, username, password, roles, order);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}


	public void setName(String name) {
		this.name=name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email= email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone= phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address= address;
	}
	
	
}
