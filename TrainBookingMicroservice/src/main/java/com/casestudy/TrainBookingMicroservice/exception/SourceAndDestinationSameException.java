package com.casestudy.TrainBookingMicroservice.exception;

public class SourceAndDestinationSameException extends Exception{

	public SourceAndDestinationSameException(String message) {
		super(message);
	}
}
