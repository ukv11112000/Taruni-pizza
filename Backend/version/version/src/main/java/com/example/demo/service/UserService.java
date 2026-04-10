package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.SuperAdminCannotBeRemovedException;
import com.example.demo.exception.UserNameAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepopository;


	
@Service
@Transactional
public class UserService {
	@Autowired
	private final UserRepopository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Autowired
	public UserService(UserRepopository userRepo) {
		this.userRepo = userRepo;
		
	}

	public User addNewUser(User user) {
		
		String pwd = user.getPassword();
		String encryptPwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		if( userRepo.findByUsername(user.getUsername()).orElse(null) != null) 
			throw  new UserNameAlreadyExistsException();
		return userRepo.save(user);

	}

	public List<User> viewUsers() {
		return userRepo.findAll();
	}

	public String signin(User user) {
		User k=getUserbyName(user.getUsername());
	
		if(k==null) {
			throw new UserNotFoundException("No User Found With Name "+user.getUsername());
		}
		
		return "Welcome "+user.getUsername()+" ,you have successfully logged in!!";
	}

	public User getUserbyName(String username) {
		
		return userRepo.findByUsername(username).orElse(null);
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public UserRepopository getUserRepo() {
		return userRepo;
	}


	public String forgotPassword(User user){
		User ruser=getUserbyID(user.getUserId());
		if(ruser==null) {
			return null;
		}
		String newpwd=user.getPassword();
		String encryptPwd = passwordEncoder.encode(newpwd);
		ruser.setPassword(encryptPwd);
		return "Password reset Successfull";
	
	}

	public String deleteUser(int id) {
		User user=getUserbyID(id);
		if(user==null) {
			return null;
		}
		else if(user.getRoles().equals("ROLE_SUPERADMIN")) {
			throw new SuperAdminCannotBeRemovedException();
		}
		userRepo.delete(user);
		return "User "+user.getUsername()+" Deleted SuccessFully";
	}
	
	
	public User getUserbyID(int id) {
		return userRepo.findById(id).orElse(null);
		
	}
	
	

	

}