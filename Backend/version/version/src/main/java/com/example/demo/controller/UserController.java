package com.example.demo.controller;
import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepopository;
import com.example.demo.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;

	private final UserRepopository userRepository;
    protected static final String DEFAULT_ROLE = "ROLE_USER";	
    protected static final String ADMIN_ACCESS = "ROLE_ADMIN";
    protected static final String SUPERADMIN_ACCESS = "ROLE_SUPERADMIN";
    
	public UserController(UserService userService, UserRepopository userRepo) {
		this.userService=userService;
		this.userRepository = userRepo;
	}
	
	
	@PostMapping("/addadmin")
	@PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
	public ResponseEntity<User> addAdmin(@RequestBody UserDto user){
		user.getUser().setRoles(ADMIN_ACCESS);
		User newuser = userService.addNewUser(user.getUser());
			return new ResponseEntity <>(newuser,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity <List<User>>viewUsers(){
		List<User> users=userService.viewUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> signin(@RequestBody UserDto user){
		String msg = userService.signin(user.getUser());
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<User> signout(){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/forgotpassword")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_SUPERADMIN')")
	public ResponseEntity<?>forgotPassword(@RequestBody UserDto user){
		String msg=userService.forgotPassword(user.getUser());
		int id=user.getUser().getUserId();
		if(msg==null) {
			throw new UserNotFoundException("No User Found with Id"+id);
		}
		return new ResponseEntity <> (msg,HttpStatus.OK);
	}
	
	@GetMapping("/access/{userId}/{userRole}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN','ROLE_SUPERADMIN')")
    public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
        User user = userRepository.findById(userId).get();
    
        user.setRoles(userRole);
        userRepository.save(user);
        return "Hi " + user.getUsername() + " New Role assign to you by " + principal.getName();
    }
	
	@PostMapping("/addsuperadmin")
	public ResponseEntity<User> addNewuser(@RequestBody UserDto user){
		user.getUser().setRoles(SUPERADMIN_ACCESS);
		User newuser = userService.addNewUser(user.getUser());
			return new ResponseEntity <>(newuser,HttpStatus.CREATED);
	}
	@GetMapping("/test")
	public String checkCurrentUser(Principal principal){
	return principal.getName();	
	}
	@DeleteMapping("/delete/{id}")	
	@PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") int id){	
		String msg=userService.deleteUser(id);	
		if(msg==null) {
			 throw new UserNotFoundException("No User Found with Id "+id);
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);	
	}
}