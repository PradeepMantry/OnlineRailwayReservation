package com.casestudy.UserLoginMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class UserLoginMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginMicroserviceApplication.class, args);
	}

}
