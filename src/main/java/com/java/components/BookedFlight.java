package com.java.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookedFlight {

	private int id;
	private int userId;
	private int flightId;
	private String airline;
	private String passengerName;
	private String from;
	private String to;
	private LocalDate departureTime;
	private LocalDate arrivalTime;
	private boolean isFuture;

	

	public boolean isFuture() {
		return isFuture;
	}

	public void setFuture(boolean isFuture) {
		this.isFuture = isFuture;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passerngerName) {
		this.passengerName = passerngerName;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public LocalDate getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDate departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDate getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDate arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String parseDate(int arrival) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

		if (arrival == 0) {
			return departureTime.format(formatter);
		} else {
			return arrivalTime.format(formatter);
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

}
