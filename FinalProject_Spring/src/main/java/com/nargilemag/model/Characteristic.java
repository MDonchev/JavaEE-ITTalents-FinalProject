package com.nargilemag.model;

public class Characteristic {

	private int id;
	private String name;
	private String unit;
	private Integer value;
	
	public Characteristic(String name, String unit, Integer value) {
		this.name = name;
		this.unit = unit;
		this.value = value;
	}
	
	public Characteristic(int id, String name, String unit, Integer value) {
		this(name, unit, value);
		this.id = id;
	}
	public Characteristic(int id, String name, Integer value) {
		this(name, value);
		this.id = id;
	}
	public Characteristic(String name, Integer value) {
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
	
}
