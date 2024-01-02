package com.casestudy.TrainBookingMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TrainBookingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainBookingMicroserviceApplication.class, args);
	}
}
