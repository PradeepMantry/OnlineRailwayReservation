package com.casestudy.AdminOperationsMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.AdminOperationsMicroservice.auth.LoginRequest;
import com.casestudy.AdminOperationsMicroservice.exception.NoStationAvailableException;
import com.casestudy.AdminOperationsMicroservice.exception.NoTrainScheduleFoundException;
import com.casestudy.AdminOperationsMicroservice.exception.SourceAndDestinationSameException;
import com.casestudy.AdminOperationsMicroservice.exception.TrainAlreadyScheduledException;
import com.casestudy.AdminOperationsMicroservice.model.Train;
import com.casestudy.AdminOperationsMicroservice.service.JwtService;
import com.casestudy.AdminOperationsMicroservice.service.TrainService;


@RestController
@RequestMapping("/admin")
public class TrainScheduleController {

	@Autowired
	private TrainService trainService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/scheduleTrain")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> scheduleTrain(@RequestBody Train trainObj) throws TrainAlreadyScheduledException, SourceAndDestinationSameException, NoStationAvailableException{
		return trainService.scheduleTrain(trainObj);
	}
	
	@PutMapping("/updateTrain")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateTrain(@RequestBody Train trainObj) throws NoTrainScheduleFoundException, NoStationAvailableException, SourceAndDestinationSameException{
		return trainService.updateTrain(trainObj);
	}
	
	@DeleteMapping("/deleteTrain")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteTrain(@RequestBody Train trainObj) throws NoTrainScheduleFoundException{
		return trainService.deleteTrain(trainObj);
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
}
