package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
    @Column(unique = true, length = 32)
	private String username;  
	private String password;
	private String roles;

	
	
	public User(int userId, String username, String password, String roles, List<Order> order) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.order = order;
	}


	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="orderCustomerId",referencedColumnName="userId")
	public List<Order> order;  


	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public User() {
		super();

	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	



}