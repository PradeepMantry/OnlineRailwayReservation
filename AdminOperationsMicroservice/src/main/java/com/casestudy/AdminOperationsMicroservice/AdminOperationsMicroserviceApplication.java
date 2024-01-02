package com.casestudy.AdminOperationsMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminOperationsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminOperationsMicroserviceApplication.class, args);
	}

}
