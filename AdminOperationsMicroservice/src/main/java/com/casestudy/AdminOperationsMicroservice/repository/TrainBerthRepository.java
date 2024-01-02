package com.casestudy.AdminOperationsMicroservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.AdminOperationsMicroservice.model.TrainBerth;

@Repository
public interface TrainBerthRepository extends JpaRepository<TrainBerth,Integer>{

	List<TrainBerth> findByTrainNumberAndDepatureDateTime(long trainNumber, LocalDateTime depatureDateTime);

}
