package com.casestudy.UserLoginMicroservice.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.casestudy.UserLoginMicroservice.exception.UserAlreadyRegisteredException;
import com.casestudy.UserLoginMicroservice.model.User;
import com.casestudy.UserLoginMicroservice.repository.UserRepository;
import com.casestudy.UserLoginMicroservice.service.UserService;

import java.util.*;

@Component
public class UserDAO implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<String> registerUser(User userObj) throws UserAlreadyRegisteredException {
		
		//checking if a user is already registered and throwing exception if true
		List<User> userListByEmailId= userRepository.findByEmailId(userObj.getEmailId());
		if(userListByEmailId.size()==1) {
			throw new UserAlreadyRegisteredException(userObj.getEmailId()+" is already registered. Try again by entering different email id");
		}
		
		//checking if a user is already registered and throwing exception if true
		List<User> userListByMobileNo=userRepository.findByMobileNo(userObj.getMobileNo());
		if(userListByMobileNo.size()==1) {
			throw new UserAlreadyRegisteredException(userObj.getMobileNo()+" is already registered. Try again by entering different mobile number");
		}
		
		//checking if a user is already registered and throwing exception if true
//		List<User> userListByUserNameList=userRepository.findByUserName(userObj.getUserName());
//		if(userListByUserNameList.size()==1) {
//			throw new UserAlreadyRegisteredException(userObj.getUserName()+" is already registered. Try again by entering different username");
//		}
		
		//setting the role of the registering user as User since admin cannot register
		userObj.setRole("USER");
		
		userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
		// saving the user details who want to register to the database
		userRepository.save(userObj);
		return new ResponseEntity<String>("Registered Successfully!",HttpStatus.CREATED);
	}

}
