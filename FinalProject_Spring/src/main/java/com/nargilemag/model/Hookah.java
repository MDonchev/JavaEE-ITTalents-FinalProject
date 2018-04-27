package com.nargilemag.model;

public class Hookah extends Product {

	private int numberOfHoses;
	
	public Hookah(int id, String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId, int numberOfHoses) {
		super(id, name, description, price, ammountInStock, categoryId, subcategoryId);
		this.numberOfHoses = numberOfHoses;
	}
	
	public Hookah(String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId, int numberOfHoses) {
		super(name, description, price, ammountInStock, categoryId, subcategoryId);
		this.numberOfHoses = numberOfHoses;
	}
	
}