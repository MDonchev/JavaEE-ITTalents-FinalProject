package model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import util.BCrypt;

public class User {

	private int id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phoneNumber;
	private int gender_id;
	private boolean isAdmin;
	
	private List<Integer> favoriteProducts;
	
	//constructors
	public User(String username, String password, String email, String address, String phoneNumber, int gender, boolean isAdmin) {
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender_id = gender;
		this.isAdmin = isAdmin;
	}

	public User(int id, String username, String password, String email, String address, String phoneNumber, int gender, boolean isAdmin, List<Integer> favoriteProducts){
		this(username, password, email, address, phoneNumber, gender, isAdmin);
		this.id = id;
		this.favoriteProducts = favoriteProducts;
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

	public int getGender() {
		return gender_id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	
	public int getId() {
		return id;
	}
	
	public Collection<Integer> getFavorites(){
		return Collections.unmodifiableList(this.favoriteProducts);
	}
	
	public String hashPassword() {
		return BCrypt.hashpw(this.password, BCrypt.gensalt());
	}
}
