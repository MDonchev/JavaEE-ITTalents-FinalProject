package model;

public class Gender {

	private int id;
	private String name;
	
	public Gender(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
}