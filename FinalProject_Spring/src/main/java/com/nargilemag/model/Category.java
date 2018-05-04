package com.nargilemag.model;

public class Category {

	private Integer id;
	private String name;
	private Integer parent;
	
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Category(Integer id, String name, Integer parent_id) {
		this.id = id;
		this.name = name;
		this.parent= parent_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
}
