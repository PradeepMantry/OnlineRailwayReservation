package com.casestudy.TrainDetailsMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.TrainDetailsMicroservice.model.Station;
import com.casestudy.TrainDetailsMicroservice.service.StationService;

@RestController
@RequestMapping("/search")
public class StationController {

	@Autowired
	private StationService stationService;
	@PostMapping("/addStation")
	public void addStation(@RequestBody Station station) {
		stationService.addStation(station);
	}
	
	@GetMapping("/stations")
	public List<String> returnStations(){
		return stationService.returnStations();
	}
	
}
