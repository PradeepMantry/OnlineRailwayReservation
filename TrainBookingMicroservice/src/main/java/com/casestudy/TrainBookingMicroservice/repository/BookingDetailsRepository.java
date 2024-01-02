package com.casestudy.TrainBookingMicroservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.TrainBookingMicroservice.model.BookingDetails;

@Repository
public interface BookingDetailsRepository extends MongoRepository<BookingDetails, String>{

	List<BookingDetails> findByPnrNumber(String pnrNumber);

	List<BookingDetails> findByPnrNumberAndUserName(String pnrNumber, String userName);

}
