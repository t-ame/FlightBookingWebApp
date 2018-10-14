package com.java.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.PatternSyntaxException;

public class Account {

	private String firstName; 
	private String lastName;
	private String userName;
	private String password;
	private String[] address;
	private String[] SCZ;
	private LocalDate dateOfBirth;

	public enum Gender {
		FEMALE, MALE;
	}

	private Gender gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Account [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", address=" + address + ", " + SCZ + ", gender=" + gender + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getAddress() {
		return address;
	}

	public String[] getSCZ() {
		return SCZ;
	}

	public void setSCZ(String[] sCZ) {
		SCZ = sCZ;
	}

	public void setAddress(String[] address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getConcatAddress() {
		String addr = "";
		for(int i=0; i<address.length; ++i)
			addr += address[i]+"!";
		for(int i=0; i<SCZ.length; ++i)
			addr += SCZ[i]+" ";
		return addr;
	}

	public void setConcatAddress(String addr) throws PatternSyntaxException{
		String[] a = addr.split("!");
		address = new String[a.length-1];
		for(int i=0; i<a.length-1; ++i)
			address[i] = a[i];
		SCZ = a[a.length-1].split(" ");
		
	}
	
}
