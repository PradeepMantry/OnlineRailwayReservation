package com.casestudy.TrainDetailsMicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.TrainDetailsMicroservice.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer>{

	List<Station> findByStationName(String stationName);

}
