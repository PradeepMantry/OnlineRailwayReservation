 package com.casestudy.AdminOperationsMicroservice.DAO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.casestudy.AdminOperationsMicroservice.exception.NoStationAvailableException;
import com.casestudy.AdminOperationsMicroservice.exception.NoTrainScheduleFoundException;
import com.casestudy.AdminOperationsMicroservice.exception.SourceAndDestinationSameException;
import com.casestudy.AdminOperationsMicroservice.exception.TrainAlreadyScheduledException;
import com.casestudy.AdminOperationsMicroservice.model.Train;
import com.casestudy.AdminOperationsMicroservice.model.TrainBerth;
import com.casestudy.AdminOperationsMicroservice.proxy.TrainDetailsMicroserviceProxy;
import com.casestudy.AdminOperationsMicroservice.repository.TrainBerthRepository;
import com.casestudy.AdminOperationsMicroservice.repository.TrainRepository;
import com.casestudy.AdminOperationsMicroservice.service.TrainService;


@Component
public class TrainScheduleDAO implements TrainService{
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private TrainBerthRepository trainBerthRepository;

	@Autowired
	private TrainDetailsMicroserviceProxy trainDetailsMicroserviceProxy;
	
	Logger logger = LoggerFactory.getLogger(TrainScheduleDAO.class);
	
	@Override
	public ResponseEntity<String> scheduleTrain(Train trainObj) throws TrainAlreadyScheduledException,
						SourceAndDestinationSameException, NoStationAvailableException {
		
		logger.info("Scheduling a train........");
		//getting all the stations from the TrainDetailsMicroservice
		logger.info("Getting list of stations from TrainDetailsMicroservice.");
		List<String> stationsList= trainDetailsMicroserviceProxy.returnStations();
		
		// checking if the source and destination provided by the client exists in the stations list or not(throwing exception if not)
		logger.info("checking if the source and destination provided by the client exists in the stations list or not");
		if(stationsList.contains(trainObj.getTrainSourcePlace())&&stationsList.contains(trainObj.getTrainDestinationPlace())) {
			
			logger.info("checking if the source and destination provided by the client is equal or not");
			if(trainObj.getTrainSourcePlace().equalsIgnoreCase(trainObj.getTrainDestinationPlace())) {
				logger.error("Source and Destination could not be the same.");
				throw new SourceAndDestinationSameException("Source and Destination could not be the same.");
			}
			//finding if the train is already scheduled on a particular date and throwing exception if exists
			logger.info("finding if the train is already scheduled on a particular date and throwing exception if exists");
			List<Train> trainList= trainRepository.findByTrainNumberAndDepatureDateTime(trainObj.getTrainNumber(),trainObj.getDepatureDateTime());
			if(trainList.size()==1) {
				logger.error(trainObj.getTrainNumber()+" is already scheduled on "+trainObj.getDepatureDateTime()+". Try scheduling another train or on another date.");
				throw new TrainAlreadyScheduledException(
						trainObj.getTrainNumber()+" is already scheduled on "+trainObj.getDepatureDateTime()+". Try scheduling another train or on another date.");
			}
			
			for(int i=1;i<=7;i++) {
				logger.info("Filling sleeper class seats in train berth repository");
				TrainBerth trainBerth = new TrainBerth();
				trainBerth.setTrainNumber(trainObj.getTrainNumber());
				trainBerth.setTrainName(trainObj.getTrainName());
				trainBerth.setTrainSourcePlace(trainObj.getTrainSourcePlace());
				trainBerth.setTrainDestinationPlace(trainObj.getTrainDestinationPlace());
				trainBerth.setDepatureDateTime(trainObj.getDepatureDateTime());
				trainBerth.setArrivalDateTime(trainObj.getArrivalDateTime());
				trainBerth.setTrainDuration(trainObj.getTrainDuration());
				trainBerth.setKilometers(trainObj.getKilometers());
				trainBerth.setCoach("Sleeper");
				trainBerth.setSeatNumber(i);
				trainBerth.setIsAvailable("YES");
				trainBerth.setPricePerTicket(trainObj.getPriceSleeperPerKm()*trainObj.getKilometers());
				trainBerthRepository.save(trainBerth);
			}
			
			for(int i=1;i<=7;i++) {
				logger.info("Filling ThirdAC class seats in train berth repository");
				TrainBerth trainBerth = new TrainBerth();
				trainBerth.setTrainNumber(trainObj.getTrainNumber());
				trainBerth.setTrainName(trainObj.getTrainName());
				trainBerth.setTrainSourcePlace(trainObj.getTrainSourcePlace());
				trainBerth.setTrainDestinationPlace(trainObj.getTrainDestinationPlace());
				trainBerth.setDepatureDateTime(trainObj.getDepatureDateTime());
				trainBerth.setArrivalDateTime(trainObj.getArrivalDateTime());
				trainBerth.setTrainDuration(trainObj.getTrainDuration());
				trainBerth.setKilometers(trainObj.getKilometers());
				trainBerth.setCoach("ThirdAC");
				trainBerth.setSeatNumber(i);
				trainBerth.setIsAvailable("YES");
				trainBerth.setPricePerTicket(trainObj.getPriceThirdAcPerKm()*trainObj.getKilometers());
				trainBerthRepository.save(trainBerth);
			}
			
			for(int i=1;i<=7;i++) {
				logger.info("Filling SecondAC class seats in train berth repository");
				TrainBerth trainBerth = new TrainBerth();
				trainBerth.setTrainNumber(trainObj.getTrainNumber());
				trainBerth.setTrainName(trainObj.getTrainName());
				trainBerth.setTrainSourcePlace(trainObj.getTrainSourcePlace());
				trainBerth.setTrainDestinationPlace(trainObj.getTrainDestinationPlace());
				trainBerth.setDepatureDateTime(trainObj.getDepatureDateTime());
				trainBerth.setArrivalDateTime(trainObj.getArrivalDateTime());
				trainBerth.setTrainDuration(trainObj.getTrainDuration());
				trainBerth.setKilometers(trainObj.getKilometers());
				trainBerth.setCoach("SecondAC");
				trainBerth.setSeatNumber(i);
				trainBerth.setIsAvailable("YES");
				trainBerth.setPricePerTicket(trainObj.getPriceSecondAcPerKm()*trainObj.getKilometers());
				trainBerthRepository.save(trainBerth);
			}
			
			for(int i=1;i<=7;i++) {
				logger.info("Filling FirstAC class seats in train berth repository");
				TrainBerth trainBerth = new TrainBerth();
				trainBerth.setTrainNumber(trainObj.getTrainNumber());
				trainBerth.setTrainName(trainObj.getTrainName());
				trainBerth.setTrainSourcePlace(trainObj.getTrainSourcePlace());
				trainBerth.setTrainDestinationPlace(trainObj.getTrainDestinationPlace());
				trainBerth.setDepatureDateTime(trainObj.getDepatureDateTime());
				trainBerth.setArrivalDateTime(trainObj.getArrivalDateTime());
				trainBerth.setTrainDuration(trainObj.getTrainDuration());
				trainBerth.setKilometers(trainObj.getKilometers());
				trainBerth.setCoach("FirstAC");
				trainBerth.setSeatNumber(i);
				trainBerth.setIsAvailable("YES");
				trainBerth.setPricePerTicket(trainObj.getPriceFirstAcPerKm()*trainObj.getKilometers());
				trainBerthRepository.save(trainBerth);
			}
			// saving the train object to the repository
			logger.info("saving the train object to the repository");
			 trainRepository.save(trainObj);
			 logger.info("Scheduled successfully!");
			 return new ResponseEntity<String>("Scheduled successfully!",HttpStatus.CREATED);
		}else {
			logger.info("Either source station or destination station is not available");
			throw new NoStationAvailableException("Either source station or destination station is not available");
		}		
	}
	
	@Override
	public ResponseEntity<String> updateTrain(Train trainObj) throws NoTrainScheduleFoundException, NoStationAvailableException, SourceAndDestinationSameException {
		
		logger.info("Updating train schedule...........");
		//getting all the stations from the TrainDetailsMicroservice
		logger.info("Getting list of stations from TrainDetailsMicroservice.");
		List<String> stationsList= trainDetailsMicroserviceProxy.returnStations();
		
		logger.info("Getting list of trainberths from train berth repository.");
		List<TrainBerth> trainBerthList = trainBerthRepository.findByTrainNumberAndDepatureDateTime(trainObj.getTrainNumber(),trainObj.getDepatureDateTime());
		
		//finding and throwing exception if no train schedule found.
		logger.info("finding and throwing exception if no train schedule found.");
		List<Train> trainList= trainRepository.findByTrainNumberAndDepatureDateTime(trainObj.getTrainNumber(),trainObj.getDepatureDateTime());
		if(trainList.size()==0) {
			logger.error("No train schedule found.");
			throw new NoTrainScheduleFoundException("No train schedule found.");
		}
		
		// checking if the source and destination provided by the client exists in the stations list or not(throwing exception if not)
		logger.info("checking if the source and destination provided by the client exists in the stations list or not(throwing exception if not)");
		if(stationsList.contains(trainObj.getTrainSourcePlace())&&stationsList.contains(trainObj.getTrainDestinationPlace())) {
			if(trainObj.getTrainSourcePlace().equalsIgnoreCase(trainObj.getTrainDestinationPlace())) {
				logger.error("Source and Destination could not be the same.");
				throw new SourceAndDestinationSameException("Source and Destination could not be the same.");
			}
			//getting the id from the train object we want to update from the database
			logger.info("getting the id from the train object we want to update from the database");
			int foundId = trainList.get(0).getId();
			//setting the id to the train object the client has sent
			logger.info("setting the id to the train object the client has sent");
			trainObj.setId(foundId);

			//saving the train object(updating)
			logger.info("saving the train object(updating)");
			trainRepository.save(trainObj);
			for(TrainBerth trainBerth:trainBerthList) {
					trainBerth.setTrainNumber(trainObj.getTrainNumber());
					trainBerth.setTrainName(trainObj.getTrainName());
					trainBerth.setTrainSourcePlace(trainObj.getTrainSourcePlace());
					trainBerth.setTrainDestinationPlace(trainObj.getTrainDestinationPlace());
					trainBerth.setDepatureDateTime(trainObj.getDepatureDateTime());
					trainBerth.setArrivalDateTime(trainObj.getArrivalDateTime());
					trainBerth.setTrainDuration(trainObj.getTrainDuration());
					trainBerth.setKilometers(trainObj.getKilometers());
					if(trainBerth.getCoach().equalsIgnoreCase("sleeper")) {
						trainBerth.setPricePerTicket(trainObj.getKilometers()*trainObj.getPriceSleeperPerKm());
					}else if(trainBerth.getCoach().equalsIgnoreCase("thirdac")) {
						trainBerth.setPricePerTicket(trainObj.getKilometers()*trainObj.getPriceThirdAcPerKm());
					}else if(trainBerth.getCoach().equalsIgnoreCase("secondac")) {
						trainBerth.setPricePerTicket(trainObj.getKilometers()*trainObj.getPriceSecondAcPerKm());
					}else if(trainBerth.getCoach().equalsIgnoreCase("firstac")) {
						trainBerth.setPricePerTicket(trainObj.getKilometers()*trainObj.getPriceFirstAcPerKm());
					}
					
					logger.info("updating the train berth reposirtory");
					trainBerthRepository.save(trainBerth);
			}
			logger.info("Updated successfully");
			return new ResponseEntity<String>("Updated Successfully!",HttpStatus.OK);
		}else {
			logger.error("Either source station or destination station is not available");
			throw new NoStationAvailableException("Either source station or destination station is not available");
		}
		
	}

	@Override
	public ResponseEntity<String> deleteTrain(Train trainObj) throws NoTrainScheduleFoundException {
		
		logger.info("Deleting train schedule.......");
		//finding and throwing exception if no train schedule found.
		logger.info("finding and throwing exception if no train schedule found.");
		List<Train> trainList= trainRepository.findByTrainNumberAndDepatureDateTime(trainObj.getTrainNumber(),trainObj.getDepatureDateTime());
		if(trainList.size()==0) {
			logger.error("No train schedule found.");
			throw new NoTrainScheduleFoundException("No train schedule found.");
		}
		
		logger.info("getting the trainberth list from trainBerthRepository");
		List<TrainBerth> trainBerthList = trainBerthRepository.findByTrainNumberAndDepatureDateTime(trainObj.getTrainNumber(),trainObj.getDepatureDateTime());
		
		logger.info("deleting the train berth records");
		for(TrainBerth trainBerth: trainBerthList) {
			trainBerthRepository.delete(trainBerth);
		}
		//deleting the train object
		logger.info("deleting the train object");
		trainRepository.delete(trainList.get(0));
		logger.info("Deleted successfully!");
		return new ResponseEntity<String>("Deleted successfully!",HttpStatus.OK);
	}

}
