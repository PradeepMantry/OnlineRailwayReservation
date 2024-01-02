package com.casestudy.TrainDetailsMicroservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.casestudy.TrainDetailsMicroservice.model.Station;

@Service
public interface StationService {

	public void addStation( Station station);

	public List<String> returnStations();

}
