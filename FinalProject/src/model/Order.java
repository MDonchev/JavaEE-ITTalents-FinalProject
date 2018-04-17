package model;

import java.time.LocalDate;

public class Order {

	private int id;
	private LocalDate dateOfIssue;
	private String address;
	private String phoneNumber;
	private int userId;
	
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
	
	
	
}
