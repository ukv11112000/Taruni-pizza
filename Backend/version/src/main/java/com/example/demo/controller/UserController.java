package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.AuthenticationRequest;
import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepopository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.JwtUtil;
import com.example.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final UserRepopository userRepository;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    protected static final String SUPERADMIN_ACCESS = "ROLE_SUPERADMIN";

    public UserController(UserService userService,
                          UserRepopository userRepository,
                          CustomUserDetailsService userDetailsService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtTokenUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/user/addadmin/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    public ResponseEntity<String> addAdmin(@PathVariable int id) {
        logger.info("Hitted the /user/addadmin/{} endpoint", id);
        String msg = userService.addAdmin(id);
        if (msg == null) {
            throw new UserNotFoundException("No User Found with Id " + id);
        }
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/user/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERADMIN')")
    public ResponseEntity<List<User>> viewUsers() {
        logger.info("Hitted the /user/all endpoint");
        List<User> users = userService.viewUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @GetMapping("/user/logout")
    public ResponseEntity<String> signout() {
        logger.info("Hitted the /user/logout endpoint");
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/user/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody UserDto user) {
        logger.info("Hitted the /user/forgotpassword endpoint");
        String msg = userService.forgotPassword(user.getUser());
        int id = user.getUser().getUserId();

        if (msg == null) {
            throw new UserNotFoundException("No User Found with Id " + id);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/user/access/{userId}/{userRole}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERADMIN')")
    public ResponseEntity<String> giveAccessToUser(@PathVariable int userId,
                                                   @PathVariable String userRole,
                                                   Principal principal) {
        logger.info("Hitted the /user/access endpoint");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User Found with Id " + userId));

        user.setRoles(userRole);
        userRepository.save(user);

        String message = "Hi " + user.getUsername() + ", new role assigned to you by " + principal.getName();
        return ResponseEntity.ok(message);
    }

    @PostMapping("/user/addsuperadmin")
    public ResponseEntity<User> addNewuser(@RequestBody UserDto user) {
        logger.info("Hitted the /user/addsuperadmin endpoint");
        user.getUser().setRoles(SUPERADMIN_ACCESS);
        User newUser = userService.addNewUser(user.getUser());
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/test")
    public String checkCurrentUser(Principal principal) {
        return principal.getName();
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        logger.info("Hitted the /user/delete/{} endpoint", id);
        String msg = userService.deleteUser(id);

        if (msg == null) {
            logger.error("No user with id {} found", id);
            throw new UserNotFoundException("No User Found with Id " + id);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/user/currentUser/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        logger.info("Hitted the /user/currentUser/{} endpoint", username);
        User user = userService.getCurrentUser(username);

        if (user == null) {
            logger.error("No user with username {} found", username);
            throw new UserNotFoundException("No user with username " + username);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
