package com.casestudy.TrainBookingMicroservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.TrainBookingMicroservice.model.TrainBerth;

@Repository
public interface TrainBerthRepository extends JpaRepository<TrainBerth, Integer>{

	List<TrainBerth> findByTrainNameAndTrainNumberAndTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTimeAndCoach(
			String trainName,long trainNumber, String trainSourcePlace,String trainDestinationPlace,LocalDateTime depatureDateTime,String coach);
	
}
