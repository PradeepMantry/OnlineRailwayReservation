package com.casestudy.TrainDetailsMicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.casestudy.TrainDetailsMicroservice.DAO.TrainDetailsDAO;
import com.casestudy.TrainDetailsMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainDetailsMicroservice.model.Train;
import com.casestudy.TrainDetailsMicroservice.repository.TrainRepository;

@SpringBootTest
class TrainDetailsMicroserviceApplicationTests {

	@Mock
	TrainRepository trainRepository;
	
	@InjectMocks
	TrainDetailsDAO trainDetailsDAO;
	
	@Test
	public void searchTrainByDateTest() throws NoTrainFoundException{
		String date = "2023-06-20T00:00:00";
		LocalDateTime date1 = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		
		Train train = new Train(1,12345,"Duronto","Hyderabad","Mumbai",date1,date1,"10hrs",700,1,2,3,4);
		List<Train> list = new ArrayList<Train>();
		list.add(train);
		
		when(trainRepository.findByDepatureDateTime(date1)).thenReturn(list);
		ResponseEntity<List<Train>> response = trainDetailsDAO.searchTrainByDate(date1);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
	}
	
	@Test
	public void searchTrainFromToTest() throws NoTrainFoundException{
		String date = "2023-06-20T00:00:00";
		LocalDateTime date1 = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		
		Train train = new Train(1,12345,"Duronto","Hyderabad","Mumbai",date1,date1,"10hrs",700,1,2,3,4);
		List<Train> list = new ArrayList<Train>();
		list.add(train);
		
		when(trainRepository.findByTrainSourcePlaceAndTrainDestinationPlace("Hyderabad","Mumbai")).thenReturn(list);
		ResponseEntity<List<Train>> response = trainDetailsDAO.searchTrainFromTo("Hyderabad","Mumbai");
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
	}
	
	@Test
	public void searchTrainFromToDateTest() throws NoTrainFoundException{
		String date = "2023-06-20T00:00:00";
		LocalDateTime date1 = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		
		Train train = new Train(1,12345,"Duronto","Hyderabad","Mumbai",date1,date1,"10hrs",700,1,2,3,4);
		List<Train> list = new ArrayList<Train>();
		list.add(train);
		
		when(trainRepository.findByTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTime("Hyderabad","Mumbai",date1)).thenReturn(list);
		ResponseEntity<List<Train>> response = trainDetailsDAO.searchTrainFromToDate("Hyderabad","Mumbai",date1);
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
	}

}
