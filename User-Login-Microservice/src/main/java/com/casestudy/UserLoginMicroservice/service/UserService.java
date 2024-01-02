package com.casestudy.UserLoginMicroservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.UserLoginMicroservice.exception.UserAlreadyRegisteredException;
import com.casestudy.UserLoginMicroservice.model.User;

@Service
public interface UserService {
	
	public ResponseEntity<String> registerUser(User userObj) throws UserAlreadyRegisteredException;
}
