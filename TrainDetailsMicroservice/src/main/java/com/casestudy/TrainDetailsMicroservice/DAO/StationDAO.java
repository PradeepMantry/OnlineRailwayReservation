package com.casestudy.TrainDetailsMicroservice.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.TrainDetailsMicroservice.model.Station;
import com.casestudy.TrainDetailsMicroservice.repository.StationRepository;
import com.casestudy.TrainDetailsMicroservice.service.StationService;

@Component
public class StationDAO implements StationService{

	@Autowired
	private StationRepository stationRepository;
 
	@Override
	public void addStation(Station station) {
		stationRepository.save(station);
	}

	@Override
	public List<String> returnStations() {
		List<Station> stations= stationRepository.findAll();
		List<String> stationNamesList = new ArrayList<>();
		for(Station station:stations) {
			stationNamesList.add(station.getStationName());
		}
		
		return stationNamesList;
	}


}
