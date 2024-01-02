package com.casestudy.TrainBookingMicroservice.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.casestudy.TrainBookingMicroservice.exception.NoEnoughSeatsException;
import com.casestudy.TrainBookingMicroservice.exception.NoStationAvailableException;
import com.casestudy.TrainBookingMicroservice.exception.NoTicketFoundException;
import com.casestudy.TrainBookingMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainBookingMicroservice.exception.SeatsMoreThanSixException;
import com.casestudy.TrainBookingMicroservice.exception.SourceAndDestinationSameException;

@RestControllerAdvice
public class ExceptionalHandlers {

		@ExceptionHandler(NoTicketFoundException.class)
		public ResponseEntity<String> handleNoTicketFoundException(NoTicketFoundException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(SourceAndDestinationSameException.class)
		public ResponseEntity<String> handleSourceAndDestinationSameException(SourceAndDestinationSameException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(NoStationAvailableException.class)
		public ResponseEntity<String> handleNoStationAvailableException(NoStationAvailableException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(NoTrainFoundException.class)
		public ResponseEntity<String> handleNoTrainFoundException(NoTrainFoundException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(SeatsMoreThanSixException.class)
		public ResponseEntity<String> handleSeatsMoreThanSixException(SeatsMoreThanSixException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(NoEnoughSeatsException.class)
		public ResponseEntity<String> handleNoEnoughSeatsException(NoEnoughSeatsException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
