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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
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
	
	
	
}
