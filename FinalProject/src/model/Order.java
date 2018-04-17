package model;

import java.time.LocalDate;

public class Order {

	private int id;
	private LocalDate dateOfIssue;
	private String address;
	private String phoneNumber;
	private int userId;
	
	//constructors
	public Order(LocalDate dateOfIssue, String address, String phoneNumber, int userId) {
		this.dateOfIssue = dateOfIssue;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
	}

	public Order(int id, LocalDate dateOfIssue, String address, String phoneNumber, int userId) {
		this(dateOfIssue, address, phoneNumber, userId);
		this.id = id;
	}

	//getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
