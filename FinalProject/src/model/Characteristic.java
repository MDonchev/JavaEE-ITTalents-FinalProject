package model;

public class Characteristic {

	private int id;
	private String name;
	private String unit;
	private String value;
	
	public Characteristic(String name, String unit, String value) {
		this.name = name;
		this.unit = unit;
		this.value = value;
	}
	
	public Characteristic(int id, String name, String unit, String value) {
		this(name, unit, value);
		this.id = id;
	}
	
}
