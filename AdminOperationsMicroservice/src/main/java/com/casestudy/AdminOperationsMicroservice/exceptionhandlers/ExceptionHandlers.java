package com.casestudy.AdminOperationsMicroservice.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.casestudy.AdminOperationsMicroservice.exception.NoStationAvailableException;
import com.casestudy.AdminOperationsMicroservice.exception.NoTrainScheduleFoundException;
import com.casestudy.AdminOperationsMicroservice.exception.SourceAndDestinationSameException;
import com.casestudy.AdminOperationsMicroservice.exception.TrainAlreadyScheduledException;

@RestControllerAdvice
public class ExceptionHandlers {

	
		@ExceptionHandler(TrainAlreadyScheduledException.class)
		public ResponseEntity<String> handleTrainAlreadyScheduledException(TrainAlreadyScheduledException exception){
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
		
		@ExceptionHandler(NoTrainScheduleFoundException.class)
		public ResponseEntity<String> handleNoTrainScheduleFoundException(NoTrainScheduleFoundException exception){
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
}
