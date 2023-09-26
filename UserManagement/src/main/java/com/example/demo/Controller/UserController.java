package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;


@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class UserController
{
	@Autowired
	private UserService uService;

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllUsers() {
		List<User> userlist = uService.getAllUsers();

		if(userlist!=null) {
			return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
		}
		return new ResponseEntity<String>("userlist is empty", HttpStatus.NO_CONTENT);
	}



}
