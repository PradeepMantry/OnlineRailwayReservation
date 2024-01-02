package com.casestudy.AdminOperationsMicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.AdminOperationsMicroservice.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	List<User> findByEmailId(String email);
	List<User> findByMobileNo(String number);
	Optional<User> findByUserName(String userName);
}
