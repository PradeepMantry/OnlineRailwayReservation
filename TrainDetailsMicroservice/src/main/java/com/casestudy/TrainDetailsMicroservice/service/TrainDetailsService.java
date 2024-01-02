package com.casestudy.TrainDetailsMicroservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.TrainDetailsMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainDetailsMicroservice.model.TrainBerth;

@Service
public interface TrainDetailsService {

	ResponseEntity<List<TrainBerth>> searchTrainByDate(String date) throws NoTrainFoundException;

	ResponseEntity<List<TrainBerth>> searchTrainFromTo(String from, String to) throws NoTrainFoundException;

	ResponseEntity<List<TrainBerth>> searchTrainFromToDate(String from, String to, String date) throws NoTrainFoundException;

	List<TrainBerth> searchTrainFromToDateCoach(String from, String to, String date, String coach) throws NoTrainFoundException;

}
