package com.casestudy.TrainBookingMicroservice.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BookingDetails")
public class BookingDetails {


	@Id
	private String id;
	private long trainNumber;
	private String trainName;
	private String trainSourcePlace;
	private String trainDestinationPlace;
	private LocalDateTime depatureDateTime;
	private LocalDateTime arrivalDateTime;
	private String trainDuration;
	private int kilometers;
	private String coach;
	private String seatNumber;
	private double totalFare;
	private int noOfPassengers;
	private String pnrNumber;
	private String userName;
	public BookingDetails(String id, long trainNumber, String trainName, String trainSourcePlace,
			String trainDestinationPlace, LocalDateTime depatureDateTime, LocalDateTime arrivalDateTime,
			String trainDuration, int kilometers, String coach, String seatNumber, double totalFare, int noOfPassengers,
			String pnrNumber, String userName) {
		super();
		this.id = id;
		this.trainNumber = trainNumber;
		this.trainName = trainName;
		this.trainSourcePlace = trainSourcePlace;
		this.trainDestinationPlace = trainDestinationPlace;
		this.depatureDateTime = depatureDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.trainDuration = trainDuration;
		this.kilometers = kilometers;
		this.coach = coach;
		this.seatNumber = seatNumber;
		this.totalFare = totalFare;
		this.noOfPassengers = noOfPassengers;
		this.pnrNumber = pnrNumber;
		this.userName = userName;
	}
	public BookingDetails() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(long trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
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
	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public String getTrainDuration() {
		return trainDuration;
	}
	public void setTrainDuration(String trainDuration) {
		this.trainDuration = trainDuration;
	}
	public int getKilometers() {
		return kilometers;
	}
	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public String getPnrNumber() {
		return pnrNumber;
	}
	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
