package com.java.components;

import java.time.LocalDate;

public class Reservation {

	private LocalDate dateMade;
	private char classOfService;

	public LocalDate getDateMade() {
		return dateMade;
	}

	public void setDateMade(LocalDate dateMade) {
		this.dateMade = dateMade;
	}

	public char getClassOfService() {
		return classOfService;
	}

	public void setClassOfService(char classOfService) {
		this.classOfService = classOfService;
	}

}
