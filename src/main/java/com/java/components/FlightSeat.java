package com.java.components;

public class FlightSeat {

	private String seatNumber;

	private Reservation reservation;

	public boolean isEmpty() {

		return true;
	}

	public void fill() {

	}

	public void clear() {

	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
