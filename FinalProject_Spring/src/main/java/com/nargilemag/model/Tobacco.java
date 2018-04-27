package com.nargilemag.model;

public class Tobacco extends Product {

	private int quantity;
	
	public Tobacco(int id, String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId, int quantity) {
		super(id, name, description, price, ammountInStock, categoryId, subcategoryId);
		this.quantity = quantity;
	}
	
	public Tobacco(String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId, int quantity) {
		super(name, description, price, ammountInStock, categoryId, subcategoryId);
		this.quantity = quantity;
	}
	
}