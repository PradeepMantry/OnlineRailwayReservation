package com.casestudy.AdminOperationsMicroservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class TrainBerth {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	private int id;
	private long trainNumber;
	private String trainName;
	private String trainSourcePlace;
	private String trainDestinationPlace;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime depatureDateTime;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime arrivalDateTime;
	private String trainDuration;
	private int kilometers;
	private String coach;
	private int seatNumber;
	private String isAvailable;
	private double pricePerTicket;
	public TrainBerth(int id, long trainNumber, String trainName, String trainSourcePlace, String trainDestinationPlace,
			LocalDateTime depatureDateTime, LocalDateTime arrivalDateTime, String trainDuration, int kilometers,
			String coach, int seatNumber, String isAvailable, double pricePerTicket) {
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
		this.isAvailable = isAvailable;
		this.pricePerTicket = pricePerTicket;
	}
	public TrainBerth() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public double getPricePerTicket() {
		return pricePerTicket;
	}
	public void setPricePerTicket(double pricePerTicket) {
		this.pricePerTicket = pricePerTicket;
	}
	
	
}
