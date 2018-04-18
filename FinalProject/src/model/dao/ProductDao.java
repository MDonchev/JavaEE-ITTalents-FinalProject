package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.manager.DBManager;
import model.Product;


public class ProductDao {

	private static ProductDao instance;
	private static Connection con;
	
	private ProductDao() {
		con = DBManager.getInstance().getConnection();
	}
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
/*
	public List<Product> getAll() throws SQLException {
		String sql = "SELECT id, name, quantity, price FROM products;";
		Statement s = con.createStatement();
		ResultSet result = s.executeQuery(sql);
		List<Product> products = new ArrayList<>();
		while(result.next()) {
			products.add(new Product(
					result.getInt("id"), 
					result.getString("name"),
					result.getInt("quantity"),
					result.getDouble("price")));
		}
		return products;
	}

	public void decreaseQuantity(int productId) throws SQLException {
		String sql = "UPDATE products SET quantity = quantity - 1 WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, productId);
		ps.executeUpdate();
	}
*/
}