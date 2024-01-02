package com.casestudy.TrainBookingMicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.casestudy.TrainBookingMicroservice.DAO.BookingDAO;
import com.casestudy.TrainBookingMicroservice.exception.NoEnoughSeatsException;
import com.casestudy.TrainBookingMicroservice.exception.NoStationAvailableException;
import com.casestudy.TrainBookingMicroservice.exception.NoTicketFoundException;
import com.casestudy.TrainBookingMicroservice.exception.NoTrainFoundException;
import com.casestudy.TrainBookingMicroservice.exception.SeatsMoreThanSixException;
import com.casestudy.TrainBookingMicroservice.exception.SourceAndDestinationSameException;
import com.casestudy.TrainBookingMicroservice.model.BookTicket;
import com.casestudy.TrainBookingMicroservice.model.BookingDetails;
import com.casestudy.TrainBookingMicroservice.model.TrainBerth;
import com.casestudy.TrainBookingMicroservice.proxy.TrainDetailsMircoserviceProxy;
import com.casestudy.TrainBookingMicroservice.repository.BookingDetailsRepository;
import com.casestudy.TrainBookingMicroservice.repository.TrainBerthRepository;

@SpringBootTest
public class TrainBookingMicroserviceApplicationTest {

	@Mock
	private TrainBerthRepository trainBerthRepository;
	
	@Mock
	private BookingDetailsRepository bookingDetailsRepository;
	
	@Mock
	private Principal principal;
	
	@Mock
	private TrainDetailsMircoserviceProxy trainDetailsMircoserviceProxy;
	
	@InjectMocks
	private BookingDAO bookingDAO;
	
	@Test
	public void bookTicketTest() throws SourceAndDestinationSameException, NoStationAvailableException, NoTrainFoundException, NoEnoughSeatsException, SeatsMoreThanSixException{
		
		String date = "2023-06-20T10:30:00"; 
		LocalDateTime date1 = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		
		TrainBerth trainBerth = new TrainBerth(12345,"Duronto","Hyderabad","Mumbai",date1,date1,"10hr",700,"Sleeper","YES",700);
		
		BookTicket bookTicket = new BookTicket("Duronto",12345,"Hyderabad","Mumbai",date1,"Sleeper",1,"tejamantry728@gmail.com");
		
		List<TrainBerth> trainBerthList= new ArrayList<>();
		trainBerthList.add(trainBerth);
		
		List<String> stations= new ArrayList<>();
		stations.add("Hyderabad");
		stations.add("Mumbai");
		
		when(trainDetailsMircoserviceProxy.returnStations()).thenReturn(stations);
		
		when(trainBerthRepository.findByTrainNameAndTrainNumberAndTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTimeAndCoach("Duronto", 12345, "Hyderabad", "Mumbai", date1, "Sleeper")).thenReturn(trainBerthList);
		ResponseEntity<String> response = bookingDAO.bookTicket(bookTicket, principal);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getBookingDetailsTest() throws NoTicketFoundException{
		
		String date = "2023-06-20T10:30:00"; 
		LocalDateTime date1 = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		
		BookingDetails bookingDetails = new BookingDetails("1", 12345, "Duronto", "Hyderabad", "Mumbai", date1, date1, "10hrs", 700, "Sleeper", "1", 700, 2, "123456", "pradeep");
		List<BookingDetails> bookingDetailsList= new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		
		when(bookingDetailsRepository.findByPnrNumber("123456")).thenReturn(bookingDetailsList);
		ResponseEntity<BookingDetails> response = bookingDAO.getBookingDetails("123456");
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
	}
	
	@Test
	public void cancelTicketTest() throws NoTicketFoundException{
		String date = "2023-06-20T10:30:00"; 
		LocalDateTime date1 = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		
		BookingDetails bookingDetails = new BookingDetails("1", 12345, "Duronto", "Hyderabad", "Mumbai", date1, date1, "10hrs", 700, "Sleeper", "1", 700, 2, "123456", "pradeep");
		List<BookingDetails> bookingDetailsList= new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		
		when(bookingDetailsRepository.findByPnrNumberAndUserName("123456",principal.getName())).thenReturn(bookingDetailsList);
		ResponseEntity<String> response = bookingDAO.cancelTicket("123456",principal);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
