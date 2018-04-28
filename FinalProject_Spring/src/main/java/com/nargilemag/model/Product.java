package com.nargilemag.model;

import java.util.Collections;
import java.util.List;

public class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private int ammountInStock;
	private int categoryId;
	
	private List<Characteristic> characrteristics;
	
	//constructors
	public Product(int id, String name, String description, double price, int ammountInStock, int categoryId, List<Characteristic> characrteristics) {
		this(name, description, price, ammountInStock, categoryId, characrteristics);
		this.id = id;
	}
	

	public Product(String name, String description, double price, int ammountInStock, int categoryId, List<Characteristic> characrteristics) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.ammountInStock = ammountInStock;
		this.categoryId = categoryId;
		this.characrteristics = characrteristics;
	}


	//getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmmountInStock() {
		return ammountInStock;
	}

	public void setAmmountInStock(int ammountInStock) {
		this.ammountInStock = ammountInStock;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public List<Characteristic> getCharacrteristics() {
		return Collections.unmodifiableList(characrteristics);
	}
	
	
}
