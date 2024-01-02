package com.casestudy.TrainBookingMicroservice.exception;

public class NoEnoughSeatsException extends Exception{

	public NoEnoughSeatsException(String message) {
		super(message);
	}
}
