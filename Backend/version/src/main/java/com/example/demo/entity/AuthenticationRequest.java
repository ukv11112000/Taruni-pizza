package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationRequest implements Serializable {


    private String username;
    private String password;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

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

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String username, String password) {
    	
		
        this.setUsername(username);
        this.setPassword(password);
    }
}