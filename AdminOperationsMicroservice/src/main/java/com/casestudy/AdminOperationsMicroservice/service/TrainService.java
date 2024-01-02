package com.casestudy.AdminOperationsMicroservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.casestudy.AdminOperationsMicroservice.exception.NoStationAvailableException;
import com.casestudy.AdminOperationsMicroservice.exception.NoTrainScheduleFoundException;
import com.casestudy.AdminOperationsMicroservice.exception.SourceAndDestinationSameException;
import com.casestudy.AdminOperationsMicroservice.exception.TrainAlreadyScheduledException;
import com.casestudy.AdminOperationsMicroservice.model.Train;


@Service
public interface TrainService {

	public ResponseEntity<String> scheduleTrain(@RequestBody Train trainObj) throws TrainAlreadyScheduledException, SourceAndDestinationSameException, NoStationAvailableException;

	public ResponseEntity<String> updateTrain(Train trainObj) throws NoTrainScheduleFoundException, NoStationAvailableException, SourceAndDestinationSameException;

	public ResponseEntity<String> deleteTrain(Train trainObj) throws NoTrainScheduleFoundException;
}
