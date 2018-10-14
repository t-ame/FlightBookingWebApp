package com.java.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightTemplate {

	private int id;
	private String airline;
	private String from;
	private String to;
	private LocalDate departureTime;
	private LocalDate arrivalTime;
	private int seats;
	private int availableSeats;
	private float price;

	public FlightTemplate() {
		super();
	}

	public FlightTemplate(String airline, String from, String to, LocalDate departureTime,
			LocalDate arrivalTime, int seats, float price) {
		super();
		this.airline = airline;
		this.from = from;
		this.to = to;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.seats = seats;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "FlightTemplate [id=" + id + ", airline=" + airline + ", from=" + from + ", to=" + to
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", seats=" + seats
				+ ", availableSeats=" + availableSeats + ", price=" + price + "]";
	}

	public boolean hasRoom() {
		return availableSeats > 0;
	}

	public void addReservation() {

	}

	public void cancelReservation() {

	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
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

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String parseDate(int arrival) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

		if (arrival == 0) {
			return departureTime.format(formatter);
		} else {
			return arrivalTime.format(formatter);
		}
	}

}
