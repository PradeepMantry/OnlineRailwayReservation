package com.casestudy.UserLoginMicroservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.UserLoginMicroservice.exception.UserAlreadyRegisteredException;
import com.casestudy.UserLoginMicroservice.model.User;
import com.casestudy.UserLoginMicroservice.service.JwtService;
import com.casestudy.UserLoginMicroservice.service.UserService;
import com.springsecurity.springsecurity.auth.LoginRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User userObj) throws UserAlreadyRegisteredException{
		return userService.registerUser(userObj);
	}
	
	@PostMapping("/login")
	public String authenticateAndGenerateToken(@RequestBody LoginRequest loginRequest)
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(loginRequest.getUserName());
		}
		else {
			throw new UsernameNotFoundException("invalid user");
		}
		
	}
		
	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public ResponseEntity<String> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	

}
