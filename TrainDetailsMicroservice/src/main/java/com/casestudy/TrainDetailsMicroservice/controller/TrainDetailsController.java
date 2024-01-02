package com.casestudy.TrainDetailsMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.TrainDetailsMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainDetailsMicroservice.model.TrainBerth;
import com.casestudy.TrainDetailsMicroservice.service.TrainDetailsService;

@RestController
@RequestMapping("/search")
@CrossOrigin("*")
public class TrainDetailsController {

	@Autowired
	private TrainDetailsService trainDetailsService;
	
	@GetMapping("/searchTrainByDate/{date}")
	public ResponseEntity<List<TrainBerth>> searchTrainByDate(@PathVariable String date) throws NoTrainFoundException{
		 return trainDetailsService.searchTrainByDate(date);
	}
	
	@GetMapping("/searchTrainFromTo/{from}/{to}")
	public ResponseEntity<List<TrainBerth>> searchTrainFromTo(@PathVariable String from,@PathVariable String to)throws NoTrainFoundException{
		return trainDetailsService.searchTrainFromTo(from,to);
	}
	
	@GetMapping("/searchTrainFromToDate/{from}/{to}/{date}")
	public ResponseEntity<List<TrainBerth>> searchTrainFromToDate
					(@PathVariable String from,@PathVariable String to,@PathVariable String date)throws NoTrainFoundException{
		return trainDetailsService.searchTrainFromToDate(from,to,date);
	}
	
	@GetMapping("/searchTrainFromToDateCoach/{from}/{to}/{date}/{coach}")
	public List<TrainBerth> searchTrainFromToDateCoach
					(@PathVariable String from,@PathVariable String to,@PathVariable String date,@PathVariable String coach) throws NoTrainFoundException{
		return trainDetailsService.searchTrainFromToDateCoach(from,to,date,coach);
	}
	
	@ExceptionHandler(NoTrainFoundException.class)
	public ResponseEntity<String> handleNoTrainFoundException(NoTrainFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
