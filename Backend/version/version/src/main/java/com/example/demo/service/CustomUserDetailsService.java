package com.example.demo.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepopository;




@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepopository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));
    }

}