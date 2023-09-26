package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth/v1")
@CrossOrigin("*")
public class AuthenticationController 
{
	private Map<String, String> mapObj = new HashMap<String, String>();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		if(userService.addUser(user)!=null)
	{
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
		return new ResponseEntity<String>("user registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	@PostMapping("/login")
	public ResponseEntity<?> logiUser(@RequestBody User user)
	{
		String username=user.getUsername();
		String password=user.getPassword();		
	
		Authentication authentication=null;
		
		try {
			authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(Exception ex) {
			
			return new ResponseEntity<String>("no user found with the provided username or else password didn't match", HttpStatus.CONFLICT);
		}
	
		if(authentication.isAuthenticated())
		{
			String role=userService.getUserRole(username, password);
			
			String token=jwtService.generateToken(username,role);
			//String jwtToken = generateToken(user.getUsername(), user.getPassword());
			mapObj.put("Message", "User successfully logged in");
			mapObj.put("Token:", token);
			
		}

		else {
			mapObj.put("Message", "User not logged in");
			mapObj.put("Token:", null);
			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
			
		}

		return new ResponseEntity<>(mapObj, HttpStatus.OK);
	}



	@GetMapping("/getRole")
	public  String GetRole(@RequestParam String username,@RequestParam String password)
	{
		return userService.getUserRole(username,password);
	}

	@GetMapping("/IsPetNameExist/{petName}")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public boolean IsPetNameExist(@PathVariable String petName) {

		return userService.petNameExist(petName);
	}

	@PostMapping("/forgotPassword")
	public  boolean forgetPassword(@RequestParam(name="petName") String petName,@RequestParam(name="username") String username,@RequestParam(name="newPassword") String newPassword)
	{
		return userService.forgetPassword(petName,username,newPassword);
	}

	@GetMapping("/getUser")
	public User getRole(@RequestParam String username,@RequestParam String password)
	{
		return userService.getUser(username,password);
	}


}















