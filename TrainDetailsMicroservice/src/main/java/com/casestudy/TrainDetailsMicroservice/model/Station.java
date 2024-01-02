package com.casestudy.TrainDetailsMicroservice.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name="native",strategy = "native")
	private int id;
	private String stationName;
	public Station(int id, String stationName) {
		super();
		this.id = id;
		this.stationName = stationName;
	}
	public Station() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStationName() {
		return stationName;
	}
	@Override
	public String toString() {
		return "Station [id=" + id + ", stationName=" + stationName + "]";
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}