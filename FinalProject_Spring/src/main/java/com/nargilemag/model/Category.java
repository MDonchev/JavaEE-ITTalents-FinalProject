package com.nargilemag.model;

public class Category {

	private Integer id;
	private String name;
	private Integer parent_id;
	
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Category(Integer id, String name, Integer parent_id) {
		this.id = id;
		this.name = name;
		this.parent_id = parent_id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
}
