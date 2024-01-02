package com.casestudy.AdminOperationsMicroservice.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.AdminOperationsMicroservice.model.Train;


@Repository
public interface TrainRepository extends JpaRepository<Train, Integer>{

	List<Train> findByTrainNumberAndDepatureDateTime(Long trainNumber,LocalDateTime depatureDateAndTime);
}
