package com.casestudy.TrainBookingMicroservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="TrainDetailsMicroservice")
public interface TrainDetailsMircoserviceProxy {

	@GetMapping("/search/stations")
	public List<String> returnStations();
}
