package com.example.demo.controller;
import java.security.Principal;
import java.util.List;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.AuthenticationRequest;
import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepopository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.JwtUtil;
import com.example.demo.service.UserService;
import javax.servlet.http.HttpServletRequest;
import io.jsonwebtoken.impl.DefaultClaims;
import java.util.Map;
import java.util.Map.Entry;

@RestController
public class UserController {
	private final UserService userService;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final UserRepopository userRepository;
    protected static final String DEFAULT_ROLE = "ROLE_USER";	
    protected static final String ADMIN_ACCESS = "ROLE_ADMIN";
    protected static final String SUPERADMIN_ACCESS = "ROLE_SUPERADMIN";
    
    @Autowired
	private CustomUserDetailsService userDetailsService;
    @Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
	private JwtUtil jwtTokenUtil;
    
	public UserController(UserService userService, UserRepopository userRepo) {
		this.userService=userService;
		this.userRepository = userRepo;
	}
	
	@PostMapping("/user/addadmin/{id}")
	@PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
	public ResponseEntity<String> addAdmin(@PathVariable int  id){
		logger.info("Hitted the /user/addadmin{"+id+"} endpoint");
		String msg=userService.addAdmin(id);
		if(msg==null) {
			 throw new UserNotFoundException("No User Found with Id "+id);
		}
		
		return new ResponseEntity <>(HttpStatus.CREATED);
	}
	
	@GetMapping("/user/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity <List<User>>viewUsers(){
		logger.info("Hitted the /user/all endpoint");
		List<User> users=userService.viewUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}


	@RequestMapping({ "/user/login" })
	public void firstPage() {
		
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtTokenUtil.generateToken(userdetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@GetMapping("/user/logout")
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<User> signout(){
		logger.info("Hitted the /user/logout endpoint");
		jwtTokenUtil. setJwtExpirationInMs(0);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/user/forgotpassword")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<?>forgotPassword(@RequestBody UserDto user){
		logger.info("Hitted the /user/forgotpassword endpoint");
		String msg=userService.forgotPassword(user.getUser());
		int id=user.getUser().getUserId();
		if(msg==null) {
			throw new UserNotFoundException("No User Found with Id"+id);
		}
		return new ResponseEntity <> (msg,HttpStatus.OK);
	}
	
	@GetMapping("/user/access/{userId}/{userRole}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN','ROLE_SUPERADMIN')")
    public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
		logger.info("Hitted the /user/access endpoint");
        User user = userRepository.findById(userId).get();
    
        user.setRoles(userRole);
        userRepository.save(user);
        return "Hi " + user.getUsername() + " New Role assign to you by " + principal.getName();
    }
	
	@PostMapping("/user/addsuperadmin")
	public ResponseEntity<User> addNewuser(@RequestBody UserDto user){
		logger.info("Hitted the /user/addsuperadmin endpoint");
		user.getUser().setRoles(SUPERADMIN_ACCESS);
		User newuser = userService.addNewUser(user.getUser());
			return new ResponseEntity <>(newuser,HttpStatus.CREATED);
	}
	@GetMapping("/user/test")
	public String checkCurrentUser(Principal principal){
	return principal.getName();	
	}
	@DeleteMapping("/user/delete/{id}")	
	//@PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") int id){	
		logger.info("Hitted the /user/delete{"+id+"} endpoint");
		String msg=userService.deleteUser(id);	
		if(msg==null) {
			logger.error("No user with id "+id+" found");
			 throw new UserNotFoundException("No User Found with Id "+id);
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);	
	}
	
	@GetMapping("/user/currentUser/{username}")
	public ResponseEntity<User>getUser(@PathVariable("username") String username){
		logger.info("Hitted the /user/currentUser{"+username+"} endpoint");
		User user = userService.getCurrentUser(username);
		if(user==null) {
			logger.error("No user with username "+username+" found");
			throw new UserNotFoundException("No user with username "+username);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
	
	
	
}