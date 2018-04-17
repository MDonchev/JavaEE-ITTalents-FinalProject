package model;

import java.util.List;

public class User {

	private int id;
	private String username;
	private String email;
	private String address;
	private String phoneNumber;
	private String sex;
	private boolean isAdmin;
	
	private List<Product> favoriteProducts;
	private List<Product> shoppingCart;
	
	//constructors
	public User(String username, String email, String address, String phoneNumber, String sex, boolean isAdmin, List<Product> favoriteProducts, List<Product> shoppingCart) {
		this.username = username;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.sex = sex;
		this.isAdmin = isAdmin;
		this.favoriteProducts = favoriteProducts;
		this.shoppingCart = shoppingCart;
	}
	
	public User(int id, String username, String email, String address, String phoneNumber, String sex, boolean isAdmin, List<Product> favoriteProducts, List<Product> shoppingCart) {
		this(username, email, address, phoneNumber, sex, isAdmin, favoriteProducts, shoppingCart);
		this.id = id;
	}

	//getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Product> getFavoriteProducts() {
		return favoriteProducts;
	}

	public void setFavoriteProducts(List<Product> favoriteProducts) {
		this.favoriteProducts = favoriteProducts;
	}

	public List<Product> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<Product> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	
}
