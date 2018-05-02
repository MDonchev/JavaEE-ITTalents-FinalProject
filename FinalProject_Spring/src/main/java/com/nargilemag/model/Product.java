package com.nargilemag.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product {

	private int id;
	private String name;
	private String description;
	private String imgURL;
	private double price;
	private int ammountInStock;
	private int categoryId;
	private int discountPercent;
	
	private List<Characteristic> characteristics;
	
	//constructors
	public Product() {
		this.characteristics = new ArrayList<>();
	}
	
	public Product(int id, String name, String description, double price, int ammountInStock, int categoryId,
					List<Characteristic> characteristics, String url, int discountPercent) {
		this(name, description, price, ammountInStock, categoryId, characteristics,  url, discountPercent);
		this.id = id;
	}
	
	public Product(String name, String description, double price, int ammountInStock, int categoryId,
					List<Characteristic> characteristics, String url, int discountPercent) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.ammountInStock = ammountInStock;
		this.categoryId = categoryId;
		this.characteristics = characteristics;
		this.imgURL = url;
		this.discountPercent = discountPercent;
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


	public List<Characteristic> getCharacteristics() {
		return Collections.unmodifiableList(characteristics);
	}
	
	public void setCharacteristics(List<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}
	
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ammountInStock;
		result = prime * result + categoryId;
		result = prime * result + ((characteristics == null) ? 0 : characteristics.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (ammountInStock != other.ammountInStock)
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (characteristics == null) {
			if (other.characteristics != null)
				return false;
		} else if (!characteristics.equals(other.characteristics))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	
	
}
