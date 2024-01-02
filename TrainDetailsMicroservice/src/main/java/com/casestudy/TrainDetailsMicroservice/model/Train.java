package com.casestudy.TrainDetailsMicroservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Train {

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
	private double priceSleeperPerKm;
	private double priceThirdAcPerKm;
	private double priceSecondAcPerKm;
	private double priceFirstAcPerKm;
	public Train(int id, long trainNumber, String trainName, String trainSourcePlace, String trainDestinationPlace,
			LocalDateTime depatureDateTime, LocalDateTime arrivalDateTime, String trainDuration, int kilometers,
			double priceSleeperPerKm, double priceThirdAcPerKm, double priceSecondAcPerKm, double priceFirstAcPerKm) {
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
		this.priceSleeperPerKm = priceSleeperPerKm;
		this.priceThirdAcPerKm = priceThirdAcPerKm;
		this.priceSecondAcPerKm = priceSecondAcPerKm;
		this.priceFirstAcPerKm = priceFirstAcPerKm;
	}
	public Train() {
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
	public double getPriceSleeperPerKm() {
		return priceSleeperPerKm;
	}
	public void setPriceSleeperPerKm(double priceSleeperPerKm) {
		this.priceSleeperPerKm = priceSleeperPerKm;
	}
	public double getPriceThirdAcPerKm() {
		return priceThirdAcPerKm;
	}
	public void setPriceThirdAcPerKm(double priceThirdAcPerKm) {
		this.priceThirdAcPerKm = priceThirdAcPerKm;
	}
	public double getPriceSecondAcPerKm() {
		return priceSecondAcPerKm;
	}
	public void setPriceSecondAcPerKm(double priceSecondAcPerKm) {
		this.priceSecondAcPerKm = priceSecondAcPerKm;
	}
	public double getPriceFirstAcPerKm() {
		return priceFirstAcPerKm;
	}
	public void setPriceFirstAcPerKm(double priceFirstAcPerKm) {
		this.priceFirstAcPerKm = priceFirstAcPerKm;
	}
	
	
	
	
	
	
	
	
}
