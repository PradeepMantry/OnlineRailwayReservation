package com.casestudy.TrainBookingMicroservice.exception;

public class SeatsMoreThanSixException extends Exception{

	public SeatsMoreThanSixException(String message) {
		super(message);
	}
}
