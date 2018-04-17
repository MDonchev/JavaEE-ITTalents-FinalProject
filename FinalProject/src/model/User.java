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
	
	
}
