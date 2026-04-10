package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserDto {

	private User user;

	public UserDto() {
		super();
	}

	public UserDto(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
