package com.example.demo.service;


import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.User;
import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepopository;




@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepopository userRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));
    }
    
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return new User("foo", "foo",new ArrayList<>());
//    }

}