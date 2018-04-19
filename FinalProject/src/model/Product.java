package model;

public abstract class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private int ammountInStock;
	private int categoryId;
	private int subcategoryId;
	
	//constructors
	public Product(int id, String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId) {
		this(name, description, price, ammountInStock, categoryId, subcategoryId);
		this.id = id;
	}
	

	public Product(String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.ammountInStock = ammountInStock;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
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
	
	
	
}
