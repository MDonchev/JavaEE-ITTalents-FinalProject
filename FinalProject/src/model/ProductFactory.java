package model;

import java.sql.SQLException;

import model.dao.ProductDao;

public class ProductFactory {

	public static final Product createProduct(int id, String name, String description, double price, int ammountInStock, int categoryId,
			int subcategoryId, int characteristic) throws SQLException, NullPointerException {
		
		String categoryValue = ProductDao.getInstance().getCategoryById(categoryId);
		int characteristicValue = ProductDao.getInstance().getProductCharacteristicById(id);
		
		switch (categoryValue.toLowerCase()) {
		case "hookah":
			return new Hookah(name, description, price, ammountInStock, categoryId, subcategoryId, characteristicValue);
		case "tobacco":
			return new Tobacco(name, description, price, ammountInStock, categoryId, subcategoryId, characteristicValue);
		case "box":
			return new BoxOfCubes(name, description, price, ammountInStock, categoryId, subcategoryId, characteristicValue);
		default:
			return null;
		}
		
	}
}
