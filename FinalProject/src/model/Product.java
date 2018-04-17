package model;

public class Product {

	private int id;
	private String name;
	private String description;
	private String category;
	private String subcategory;
	private double price;
	private int ammountInStock;
	private int categoryId;
	
	//constructors
	
	public Product(String name, String description, String category, String subcategory, double price, int ammountInStock, int categoryId) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.subcategory = subcategory;
		this.price = price;
		this.ammountInStock = ammountInStock;
		this.categoryId = categoryId;
	}

	public Product(int id, String name, String description, String category, String subcategory, double price, int ammountInStock, int categoryId) {
		this(name, description, category, subcategory, price, ammountInStock, categoryId);
		this.id = id;
	}
	
	//getters and setters
	
}
