package com.casestudy.TrainBookingMicroservice.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.TrainBookingMicroservice.auth.LoginRequest;
import com.casestudy.TrainBookingMicroservice.exception.NoEnoughSeatsException;
import com.casestudy.TrainBookingMicroservice.exception.NoStationAvailableException;
import com.casestudy.TrainBookingMicroservice.exception.NoTicketFoundException;
import com.casestudy.TrainBookingMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainBookingMicroservice.exception.SeatsMoreThanSixException;
import com.casestudy.TrainBookingMicroservice.exception.SourceAndDestinationSameException;
import com.casestudy.TrainBookingMicroservice.model.BookTicket;
import com.casestudy.TrainBookingMicroservice.model.BookingDetails;
import com.casestudy.TrainBookingMicroservice.service.BookingService;
import com.casestudy.TrainBookingMicroservice.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;

	
	@PostMapping("/bookTicket")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> bookTicket(@Valid @RequestBody BookTicket bookTicket,Principal principal) throws SourceAndDestinationSameException, NoStationAvailableException, NoTrainFoundException, NoEnoughSeatsException, SeatsMoreThanSixException{
		return bookingService.bookTicket(bookTicket,principal);
	}
	
	@GetMapping("/getTicket/{pnrNumber}")
//	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable String pnrNumber) throws NoTicketFoundException{
		return bookingService.getBookingDetails(pnrNumber);
	}
	
	@DeleteMapping("/cancelTicket/{pnrNumber}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> cancelTicket(@PathVariable String pnrNumber,Principal principal)throws NoTicketFoundException{
		return bookingService.cancelTicket(pnrNumber,principal);
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
