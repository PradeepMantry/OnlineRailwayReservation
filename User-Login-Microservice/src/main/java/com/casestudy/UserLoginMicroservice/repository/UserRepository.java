package com.casestudy.UserLoginMicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.UserLoginMicroservice.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	List<User> findByEmailId(String email);
	List<User> findByMobileNo(String number);
	Optional<User> findByUserName(String userName);
}
