package com.casestudy.TrainBookingMicroservice.service;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.TrainBookingMicroservice.exception.NoEnoughSeatsException;
import com.casestudy.TrainBookingMicroservice.exception.NoStationAvailableException;
import com.casestudy.TrainBookingMicroservice.exception.NoTicketFoundException;
import com.casestudy.TrainBookingMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainBookingMicroservice.exception.SeatsMoreThanSixException;
import com.casestudy.TrainBookingMicroservice.exception.SourceAndDestinationSameException;
import com.casestudy.TrainBookingMicroservice.model.BookTicket;
import com.casestudy.TrainBookingMicroservice.model.BookingDetails;
import com.casestudy.TrainBookingMicroservice.model.TrainBerth;

@Service
public interface BookingService {

	public ResponseEntity<String> bookTicket(BookTicket bookTicket,Principal principal) throws SourceAndDestinationSameException, NoStationAvailableException, NoTrainFoundException, NoEnoughSeatsException, SeatsMoreThanSixException;

	public ResponseEntity<BookingDetails> getBookingDetails(String pnrNumber) throws NoTicketFoundException;

	public ResponseEntity<String> cancelTicket(String pnrNumber,Principal principal) throws NoTicketFoundException;

	
}
