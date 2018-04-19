package model;

public class BoxOfCubes extends Product {

	private int numberOfCubes;
	
	public BoxOfCubes(int id, String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId, int numberOfCubes) {
		super(id, name, description, price, ammountInStock, categoryId, subcategoryId);
		this.numberOfCubes = numberOfCubes;
	}

	public BoxOfCubes(String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId, int numberOfCubes) {
		super(name, description, price, ammountInStock, categoryId, subcategoryId);
		this.numberOfCubes = numberOfCubes;
	}
	
}
