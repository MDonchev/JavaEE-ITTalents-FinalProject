package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nargilemag.model.Product;
import com.nargilemag.model.ProductFactory;


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
	
	public String getCategoryById(int id) throws SQLException {
		String sql = "SELECT name FROM categories WHERE id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery().getString(1);
	}
	
	public int getProductCharacteristicById(int id) throws SQLException {
		String sql = "SELECT value FROM products_have_characteristics WHERE products_id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery().getInt(1);
	}
	
	public List<Product> getAllProductsByCategoryId(int id) throws SQLException{
		String sql = "SELECT p.id, p.name, description, price, ammount_in_stock, category_id, categories_id, value FROM categories"
				+ "JOIN products p ON categories_id = ?"
				+ "JOIN products_have_characteristics c ON p.id = c.id";
		ArrayList<Product> products = new ArrayList();
		
		try(PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				products.add(ProductFactory.createProduct(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getInt("ammount_in_stock"),
						id,
						rs.getInt("categories_id"),
						rs.getInt("value")));
			}
			
		}
		
		return products;
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