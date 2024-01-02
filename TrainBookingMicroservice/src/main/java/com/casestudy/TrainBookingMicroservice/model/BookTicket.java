package com.casestudy.TrainBookingMicroservice.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class BookTicket {

	@NotEmpty
	private String trainName;
	@NotEmpty
	private long trainNumber;
	@NotEmpty
	private String trainSourcePlace;
	@NotEmpty
	private String trainDestinationPlace;
	@NotEmpty
	private LocalDateTime depatureDateTime;
	@NotEmpty
	private String coach;
	@NotEmpty
	private int noOfPassengers;
	@NotEmpty
	private String emailId;
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public long getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(long trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getTrainSourcePlace() {
		return trainSourcePlace;
	}
	public void setTrainSourcePlace(String trainSourcePlace) {
		this.trainSourcePlace = trainSourcePlace;
	}
	public String getTrainDestinationPlace() {
		return trainDestinationPlace;
	}
	public void setTrainDestinationPlace(String trainDestinationPlace) {
		this.trainDestinationPlace = trainDestinationPlace;
	}
	public LocalDateTime getDepatureDateTime() {
		return depatureDateTime;
	}
	public void setDepatureDateTime(LocalDateTime depatureDateTime) {
		this.depatureDateTime = depatureDateTime;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public BookTicket(String trainName, long trainNumber, String trainSourcePlace, String trainDestinationPlace,
			LocalDateTime depatureDateTime, String coach, int noOfPassengers, String emailId) {
		super();
		this.trainName = trainName;
		this.trainNumber = trainNumber;
		this.trainSourcePlace = trainSourcePlace;
		this.trainDestinationPlace = trainDestinationPlace;
		this.depatureDateTime = depatureDateTime;
		this.coach = coach;
		this.noOfPassengers = noOfPassengers;
		this.emailId = emailId;
		
	}
	public BookTicket() {
		super();
	}
	
	
	
}
