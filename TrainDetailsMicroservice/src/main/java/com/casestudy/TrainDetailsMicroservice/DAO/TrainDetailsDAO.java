package com.casestudy.TrainDetailsMicroservice.DAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.casestudy.TrainDetailsMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainDetailsMicroservice.model.Train;
import com.casestudy.TrainDetailsMicroservice.model.TrainBerth;
import com.casestudy.TrainDetailsMicroservice.repository.TrainBerthRepository;
import com.casestudy.TrainDetailsMicroservice.service.TrainDetailsService;

@Component
public class TrainDetailsDAO implements TrainDetailsService{
	
	@Autowired
	private TrainBerthRepository trainBerthRepository;

	@Override
	public ResponseEntity<List<TrainBerth>> searchTrainByDate(String date) throws NoTrainFoundException {
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		LocalDate date2 = LocalDate.parse(date, inputFormatter);
		LocalDateTime dateTime = date2.atTime(20, 30, 0, 0);
		String formattedDateTime = dateTime.format(outputFormatter);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime date1 = LocalDateTime.parse(formattedDateTime,formatter);
		
		List<TrainBerth> trainList = trainBerthRepository.findByDepatureDateTime(date1);
		if(trainList.size()==0) {
			throw new NoTrainFoundException("No trains found in "+date);
		}
		
		return new ResponseEntity<List<TrainBerth>>(trainList,HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<List<TrainBerth>> searchTrainFromTo(String from, String to) throws NoTrainFoundException {
		List<TrainBerth> trainList = trainBerthRepository.findByTrainSourcePlaceAndTrainDestinationPlace(from,to);
		if(trainList.size()==0) {
			throw new NoTrainFoundException("No trains found from "+from+" to "+to);
		}
		
		return new ResponseEntity<List<TrainBerth>>(trainList,HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<List<TrainBerth>> searchTrainFromToDate(String from, String to, String date) throws NoTrainFoundException {
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		LocalDate date2 = LocalDate.parse(date, inputFormatter);
		LocalDateTime dateTime = date2.atTime(20, 30, 0, 0);
		String formattedDateTime = dateTime.format(outputFormatter);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime date1 = LocalDateTime.parse(formattedDateTime,formatter);
		
		List<TrainBerth> trainList = trainBerthRepository.findByTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTime(from,to,date1);
		if(trainList.size()==0) {
			throw new NoTrainFoundException("No trains found");
		}
		
		return new ResponseEntity<List<TrainBerth>>(trainList,HttpStatus.FOUND);
	}

	@Override
	public List<TrainBerth> searchTrainFromToDateCoach(String from, String to, String date,
			String coach) throws NoTrainFoundException {
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		LocalDate date2 = LocalDate.parse(date, inputFormatter);
		LocalDateTime dateTime = date2.atTime(20, 30, 0, 0);
		String formattedDateTime = dateTime.format(outputFormatter);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime date1 = LocalDateTime.parse(formattedDateTime,formatter);
		
		
		List<TrainBerth> trainList = trainBerthRepository.findByTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTimeAndCoach(from,to,date1,coach);
		if(trainList.size()==0) {
			throw new NoTrainFoundException("No trains found");
		}
		
		return trainList;
	}

}
