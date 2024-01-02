package com.casestudy.TrainBookingMicroservice.DAO;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
import com.casestudy.TrainBookingMicroservice.service.BookingService;
import com.casestudy.TrainBookingMicroservice.service.EmailService;

@Component
public class BookingDAO implements BookingService{

	@Autowired
	private TrainDetailsMircoserviceProxy trainDetailsMircoserviceProxy;
	
	@Autowired
	private TrainBerthRepository trainBerthRepository;
	
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	
	@Autowired
	private EmailService emailService;
	@Override
	public ResponseEntity<String> bookTicket(BookTicket bookTicket,Principal principal) throws SourceAndDestinationSameException, NoStationAvailableException, NoTrainFoundException, NoEnoughSeatsException, SeatsMoreThanSixException {
		
		List<String> stationsList = trainDetailsMircoserviceProxy.returnStations();
		
		BookingDetails bookingDetails= new BookingDetails();
		
		if(stationsList.contains(bookTicket.getTrainSourcePlace())&&stationsList.contains(bookTicket.getTrainDestinationPlace())) {
			if(bookTicket.getTrainSourcePlace().equalsIgnoreCase(bookTicket.getTrainDestinationPlace())) {
				throw new SourceAndDestinationSameException("Source and Destination could not be the same.");
			}
			
			if(bookTicket.getNoOfPassengers()>6) {
				throw new SeatsMoreThanSixException("You cannot book more than six tickets.");
			}
			
			List<TrainBerth> trainBerthList = 
					trainBerthRepository.findByTrainNameAndTrainNumberAndTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTimeAndCoach(
							bookTicket.getTrainName(),bookTicket.getTrainNumber(), bookTicket.getTrainSourcePlace(), bookTicket.getTrainDestinationPlace(),
							bookTicket.getDepatureDateTime(), bookTicket.getCoach());
			
			if(trainBerthList.size()==0) {
				throw new NoTrainFoundException(
						"No trains found from "+bookTicket.getTrainSourcePlace()
						+" to "+bookTicket.getTrainDestinationPlace()+" on "
						+bookTicket.getDepatureDateTime()+" for "+bookTicket.getCoach());
			}
			
			int availableSeats=0;
			for(TrainBerth trainBerth:trainBerthList) {
				if(trainBerth.getIsAvailable().equalsIgnoreCase("yes")) {
					availableSeats++;
				}
			}
			
			if(availableSeats>=bookTicket.getNoOfPassengers()) {
				UUID uuid = UUID.randomUUID();
				String pnrNumber = uuid.toString().substring(0,8);
				
				int temp = bookTicket.getNoOfPassengers();
				boolean loopBreak=false;
				while(temp>0 && !loopBreak) {
					for(TrainBerth trainBerth:trainBerthList) {
						if(trainBerth.getIsAvailable().equalsIgnoreCase("yes")) {
							trainBerth.setIsAvailable("NO");
							trainBerthRepository.save(trainBerth);
							if(bookingDetails.getSeatNumber()==null) {
								bookingDetails.setSeatNumber(trainBerth.getSeatNumber()+"");
							}else {
								bookingDetails.setSeatNumber(bookingDetails.getSeatNumber()+","+trainBerth.getSeatNumber());
							}
							temp--;
							if(temp==0) {
								loopBreak=true;
								break;
							}
						}
					}
				}
				
				bookingDetails.setTrainNumber(trainBerthList.get(0).getTrainNumber());
				bookingDetails.setTrainName(trainBerthList.get(0).getTrainName());
				bookingDetails.setTrainSourcePlace(trainBerthList.get(0).getTrainSourcePlace());
				bookingDetails.setTrainDestinationPlace(trainBerthList.get(0).getTrainDestinationPlace());
				bookingDetails.setDepatureDateTime(trainBerthList.get(0).getDepatureDateTime());
				bookingDetails.setArrivalDateTime(trainBerthList.get(0).getArrivalDateTime());
				bookingDetails.setTrainDuration(trainBerthList.get(0).getTrainDuration());
				bookingDetails.setKilometers(trainBerthList.get(0).getKilometers());
				bookingDetails.setCoach(trainBerthList.get(0).getCoach());
				bookingDetails.setTotalFare(bookTicket.getNoOfPassengers()*trainBerthList.get(0).getPricePerTicket());
				bookingDetails.setNoOfPassengers(bookTicket.getNoOfPassengers());
				bookingDetails.setPnrNumber(pnrNumber);
				bookingDetails.setUserName(principal.getName());
				
				bookingDetailsRepository.save(bookingDetails);
				
				try {
					String subject="Booking Confirmation - PNR:"+pnrNumber;
					String body= "<html><head><style>" +
					             "body { font-family: 'Arial', sans-serif; background-color: #f5f5f5; }" +
					             "h1 { color: #333; text-align: center; }" +
					             "p { color: #666; }" +
								 "</style></head><body>" +
				                 "<h1>Ticket Details</h1>" +
				                 "<p><strong>Train Name:</strong> " +bookingDetails.getTrainName() + "</p>" +
				                 "<p><strong>Train Number:</strong> " +bookingDetails.getTrainNumber() + "</p>" +
				                 "<p><strong>From:</strong> " + bookingDetails.getTrainSourcePlace() + "</p>" +
				                 "<p><strong>To:</strong> " + bookingDetails.getTrainDestinationPlace() + "</p>" +
				                 "<p><strong>Depature Date and Time:</strong> " + bookingDetails.getDepatureDateTime() + "</p>" +
				                 "<p><strong>Arrival Date and Time:</strong> " + bookingDetails.getArrivalDateTime() + "</p>" +
				                 "<p><strong>Seat Numbers:</strong> " + bookingDetails.getSeatNumber() + "</p>" +
				                 "<p><strong>Coach Type:</strong> " + bookingDetails.getCoach() + "</p>" +
				                 "<p><strong>Total Fare:</strong> " + bookingDetails.getTotalFare() + "</p>" +
				                 "<p><strong>No. of Passengers:</strong> " + bookingDetails.getNoOfPassengers() + "</p>" +				                 
				                 "<p><strong>Kilometers:</strong> " + bookingDetails.getKilometers() + "</p>" +
				                 "<p><strong>Duration:</strong> " + bookingDetails.getTrainDuration() + "</p>" +
				                 "</body></html>";
					emailService.sendEmail(bookTicket.getEmailId(), subject, body);
				}catch (Exception e) {
					e.printStackTrace();
				}
								
			}else {
				throw new NoEnoughSeatsException(
						bookTicket.getNoOfPassengers()+" tickets are not available in "+bookTicket.getCoach()+". Try booking for any other train or coach");
			}
			return new ResponseEntity<String>("Ticket booked successfully. PNR: "+
							bookingDetails.getPnrNumber()+". Ticket sent to "+bookTicket.getEmailId(),HttpStatus.OK);
		}else {
			throw new NoStationAvailableException("Either source station or destination station is not available");
		}	
	}
	@Override
	public ResponseEntity<BookingDetails> getBookingDetails(String pnrNumber) throws NoTicketFoundException {
		// TODO Auto-generated method stub
		List<BookingDetails> bookingDetails = bookingDetailsRepository.findByPnrNumber(pnrNumber);
		if(bookingDetails.size()==0) {
			throw new NoTicketFoundException("PNR number is wrong. Try again by entering the correct PNR");
		}
		
		return new ResponseEntity<BookingDetails>(bookingDetails.get(0),HttpStatus.FOUND);
	}
	@Override
	public ResponseEntity<String> cancelTicket(String pnrNumber,Principal principal) throws NoTicketFoundException {
		// TODO Auto-generated method stub
		List<BookingDetails> bookingDetails = bookingDetailsRepository.findByPnrNumberAndUserName(pnrNumber,principal.getName());
		if(bookingDetails.size()==0) {
			throw new NoTicketFoundException("PNR number is wrong. Try again by entering the correct PNR");
		}
		
		bookingDetailsRepository.delete(bookingDetails.get(0));
		
		String seatNumbersString = bookingDetails.get(0).getSeatNumber();
		String[] seatNumbersArray=seatNumbersString.split(",");
		
		List<Integer> seatNumbersList=new ArrayList<>();
		
		for(String eachSeatNumberString:seatNumbersArray) {
			seatNumbersList.add(Integer.parseInt(eachSeatNumberString.trim()));
		}
		
		List<TrainBerth> trainBerthList=trainBerthRepository.
				findByTrainNameAndTrainNumberAndTrainSourcePlaceAndTrainDestinationPlaceAndDepatureDateTimeAndCoach(
				bookingDetails.get(0).getTrainName(),bookingDetails.get(0).getTrainNumber(),bookingDetails.get(0).getTrainSourcePlace(),
				bookingDetails.get(0).getTrainDestinationPlace(),bookingDetails.get(0).getDepatureDateTime(),
				bookingDetails.get(0).getCoach());
		
		for(int seat:seatNumbersList) {
			for(TrainBerth trainBerth:trainBerthList) {
				if(trainBerth.getSeatNumber()==seat) {
					trainBerth.setIsAvailable("YES");
					trainBerthRepository.save(trainBerth);
				}
			}
		}
		
		return new ResponseEntity<String>("Ticket cancelled successfully.",HttpStatus.OK) ;
	}

}
