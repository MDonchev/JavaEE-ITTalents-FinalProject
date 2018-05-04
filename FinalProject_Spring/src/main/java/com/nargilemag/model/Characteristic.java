package com.nargilemag.model;

public class Characteristic {

	private int id;
	private String name;
	private String unit;
	private Integer value;
	private Integer category;
	
	public Characteristic(String name, String unit, Integer value, Integer category) {
		this.name = name;
		this.unit = unit;
		this.value = value;
		this.category = category;
	}
	
	public Characteristic(int id, String name, String unit, Integer value, Integer category) {
		this(name, unit, value, category);
		this.id = id;
	}
	public Characteristic(int id, String name, Integer value, Integer category) {
		this(name, value, category);
		this.id = id;
	}
	public Characteristic(String name, Integer value, Integer category) {
		this.name = name;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit;
	}

	public Integer getValue() {
		return value;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	
}
