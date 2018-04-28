package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nargilemag.model.Product;


public enum ProductDao {

	INSTANCE;
	
	private Connection connection;
	
	private ProductDao() {
		connection = DBManager.INSTANCE.getConnection();
	}
	
	public String getCategoryById(int id) throws SQLException {
		String sql = "SELECT name FROM categories WHERE id = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery().getString(1);
	}
	
	public int getProductCharacteristicById(int id) throws SQLException {
		String sql = "SELECT value FROM products_have_characteristics WHERE products_id = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery().getInt(1);
	}
	
	public List<Product> getAllProductsByCategoryId(int id) throws SQLException {
		
		String sql = "SELECT p.id, p.name, description, price, ammount_in_stock, category_id FROM products p"
				+ "JOIN categories c ON category_id = ?";
		
		ArrayList<Product> products = new ArrayList();
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				products.add(new Product(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getInt("ammount_in_stock"),
						id,
						rs.getInt("categories_id"),
						CharacteristicDao.INSTANCE.getCharacteristicsByProductId(id)));
			}
			
		}
		
		return products;
	}
	
/*
	public void decreaseQuantity(int productId) throws SQLException {
		String sql = "UPDATE products SET quantity = quantity - 1 WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, productId);
		ps.executeUpdate();
	}
*/
}