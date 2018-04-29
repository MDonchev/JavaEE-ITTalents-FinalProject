package com.nargilemag.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.nargilemag.util.BCrypt;

public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phoneNumber;
	private double balance;
	private int gender_id;
	private boolean isAdmin;
	
	private List<Integer> favoriteProducts;
	
	//constructors
	public User(String username, String password, String email, String address, String phoneNumber, double balance, int gender, boolean isAdmin) {
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.gender_id = gender;
		this.isAdmin = isAdmin;
	}

	public User(int id, String username, String password, String email, String address, String phoneNumber, double balance, int gender, boolean isAdmin){
		this(username, password, email, address, phoneNumber, balance, gender, isAdmin);
		this.id = id;
		this.favoriteProducts = new ArrayList<>();
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getGender() {
		return gender_id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Collection<Integer> getFavorites(){
		return Collections.unmodifiableList(this.favoriteProducts);
	}
	
	public String hashPassword() {
		return BCrypt.hashpw(this.password, BCrypt.gensalt());
	}
}