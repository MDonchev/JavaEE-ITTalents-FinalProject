package model;

import java.time.LocalDate;
import java.util.HashMap;

public class Order {

	private int id;
	private LocalDate dateOfIssue;
	private String address;
	private String phoneNumber;
	private int userId;
	
	private HashMap<Product, Integer> orderedProducts;
	
	//constructors
	public Order(LocalDate dateOfIssue, String address, String phoneNumber, int userId, HashMap<Product, Integer> orderedProducts) {
		this.dateOfIssue = dateOfIssue;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.userId = userId;
		this.orderedProducts = orderedProducts;
	}

	public Order(int id, LocalDate dateOfIssue, String address, String phoneNumber, int userId, HashMap<Product, Integer> orderedProducts) {
		this(dateOfIssue, address, phoneNumber, userId, orderedProducts);
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
