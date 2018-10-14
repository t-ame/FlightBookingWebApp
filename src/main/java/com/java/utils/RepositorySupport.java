package com.java.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import com.java.components.Account;
import com.java.components.BookedFlight;
import com.java.components.FlightTemplate;
import com.java.components.User;

public class RepositorySupport {

	
	public static List<FlightTemplate> mapToScheduled(ResultSet set) throws SQLException{
		

		List<FlightTemplate> flights = new ArrayList<>();
		FlightTemplate flight = new FlightTemplate();

			while (set.next()) {
				flight.setAirline(set.getString("airline"));
				flight.setArrivalTime(
						set.getDate("arrival_date").toLocalDate());
				flight.setDepartureTime(
						set.getDate("departure_date").toLocalDate());
				flight.setFrom(set.getString("source"));
				flight.setTo(set.getString("destination"));
				flight.setPrice(set.getFloat("cost"));
				flight.setSeats(set.getInt("seats"));
				flight.setAvailableSeats(set.getInt("available_seats"));
				flight.setId(set.getInt("id"));
				flights.add(flight);
			}
		
		
		return flights;
	}
	
	public static List<BookedFlight> mapToBooked(ResultSet set) throws SQLException{
		
		List<BookedFlight> flights = new ArrayList<>();
		BookedFlight flight = new BookedFlight();
		
			while (set.next()) {
				flight.setAirline(set.getString("airline"));
				flight.setArrivalTime(
						set.getDate("arrival_date").toLocalDate());
				flight.setDepartureTime(
						set.getDate("departure_date").toLocalDate());
				flight.setFrom(set.getString("source"));
				flight.setTo(set.getString("destination"));
				flight.setPassengerName(set.getString("passenger_name"));
				flight.setUserId(set.getInt("userId"));
				flight.setFlightId(set.getInt("flightId"));
				flight.setId(set.getInt("id"));
				if(flight.getDepartureTime().isBefore(LocalDate.now())) {
					flight.setFuture(false);
				} else {
					flight.setFuture(true);
				}
				flights.add(flight);
			}
		
		return flights;
	}
	
	public static List<User> mapToUser(ResultSet set, boolean isAdmin) throws SQLException, PatternSyntaxException{

		User user = new User();
		Account account = new Account();
		List<User> list = new ArrayList<>();
		
		while (set.next()) {
			user.setId(set.getInt("id"));
			account.setFirstName(set.getString("first_name"));
			account.setLastName(set.getString("last_name"));
			account.setUserName(set.getString("user_name"));
			account.setConcatAddress(set.getString("address"));
			account.setDateOfBirth(
					set.getDate("date_of_birth").toLocalDate());
			account.setPassword(set.getString("passwords"));
			if (set.getString("gender").equalsIgnoreCase("female")) {
				account.setGender(Account.Gender.FEMALE);
			} else {
				account.setGender(Account.Gender.MALE);
			}

			user.setAdmin(isAdmin); 
			user.setAccount(account);
			list.add(user);
		}
		
		return list;
	}
	
}
