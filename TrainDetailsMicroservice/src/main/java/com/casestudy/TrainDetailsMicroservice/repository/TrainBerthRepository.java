package com.casestudy.TrainDetailsMicroservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.TrainDetailsMicroservice.model.TrainBerth;

@Repository
public interface TrainBerthRepository extends JpaRepository<TrainBerth, Integer>{

	List<TrainBerth> findByTrainNumberAndDepatureDateTime(Long trainNumber,LocalDateTime depatureDateAndTime);

	List<TrainBerth> findByDepatureDateTime(LocalDateTime date);

	List<TrainBerth> findByTrainSourcePlaceAndTrainDestinationPlace(String from, String to);

	List<TrainBerth> findByTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTime(String from, String to,LocalDateTime date);

	List<TrainBerth> findByTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTimeAndCoach(String from, String to,
			LocalDateTime date, String coach);
}
