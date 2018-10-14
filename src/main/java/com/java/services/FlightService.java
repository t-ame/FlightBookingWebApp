package com.java.services;

import java.time.LocalDate;
import java.util.List;

import com.java.components.BookedFlight;
import com.java.components.FlightTemplate;
import com.java.components.User;
//import com.java.components.ScheduledFlight;
import com.java.exception.GeneralException;
import com.java.repositories.FlightRepository;

public class FlightService {

	private final FlightRepository repository;

	public FlightService() {
		super();
		repository = FlightRepository.getFlightRepository();
	}

	public FlightTemplate getFlightById(int id) throws GeneralException {
		return repository.getFlightById(id);
	}

	public List<FlightTemplate> getAllFlightsBetween(String departure, String arrival, LocalDate date)
			throws GeneralException {
		return repository.getAllFlightsBetween(departure, arrival, date);
	}
	
	public List<FlightTemplate> getAllFlightsToday()
			throws GeneralException {
		return repository.getAllFlightsToday();
	}
	

	public void addBooking(BookedFlight flight) throws GeneralException {
		repository.addBooking(flight);
	}

	public void addFlight(FlightTemplate flight) throws GeneralException {
		repository.addFlight(flight);
	}

	public void updateFlight(FlightTemplate flight) throws GeneralException {
		repository.updateFlight(flight);
	}

	public void deleteFlight(int flightId) throws GeneralException {
		repository.deleteFlight(flightId);
	}

	// used by customer to view booking
	public BookedFlight getBooking(int id) throws GeneralException {
		return repository.getBooking(id);
	}

	// to be used by Admin
	public List<FlightTemplate> getAllFlights() throws GeneralException {
		return repository.getAllFlights();
	}

	// to be used by Admin
	public List<BookedFlight> getAllBookings() throws GeneralException {

		return repository.getAllBookings();
	}
	
	// to be used by Admin
	public List<BookedFlight> getUserHistory(User user) throws GeneralException {

		return repository.getUserHistory(user);
	}

	public String getErrorMsg() {
		return repository.getErrorMsg();
	}

}
