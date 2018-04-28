package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nargilemag.model.Characteristic;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;


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
						CharacteristicDao.INSTANCE.getCharacteristicsByProductId(id)));
			}
			
		}
		
		return products;
	}
	
	public List<Product> getAllProducts() throws SQLException {
		
		String sql = "SELECT id, name, description, price, ammount_in_stock, category_id FROM products";
		
		ArrayList<Product> products = new ArrayList();
		
		Statement s = connection.createStatement();
		ResultSet result = s.executeQuery(sql);
			
		while(result.next()) {
				System.out.println("in cycle in cycle in cycle in cycle");
				products.add(new Product(
						result.getString("name"),
						result.getString("description"),
						result.getDouble("price"),
						result.getInt("ammount_in_stock"),
						result.getInt("category_id"),
						CharacteristicDao.INSTANCE.getCharacteristicsByProductId(result.getInt("id"))));
		}
		return products;
	}
	
	public void saveProduct(Product prod) throws SQLException {
		synchronized (connection) {
			
			connection.setAutoCommit(false);
			try {
				ResultSet result = null;
				//Insert in products table
				try(PreparedStatement ps = connection.prepareStatement("INSERT INTO products (name, description, price, ammount_in_stock, category_id) VALUES (?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS)){
					ps.setString(1, prod.getName());
					ps.setString(2, prod.getDescription());
					ps.setDouble(3, prod.getPrice());
					ps.setInt(4, prod.getAmmountInStock());
					ps.setInt(5, prod.getCategoryId());
					
					ps.executeUpdate();
					
					result = ps.getGeneratedKeys();
					result.next();
					int productId = result.getInt(1);
					
					prod.setId(productId);
				}
				
				//Insert in products_characteristics
				List<Characteristic> characteristics = prod.getCharacrteristics();
				for(Characteristic characteristic : characteristics) {
					try(PreparedStatement ps = connection.prepareStatement("INSERT INTO products_have_characteristics (products_id, characteristics_id, value) VALUES (?,?,?);")){
						System.out.println(prod.getId());
						ps.setInt(1, prod.getId());
						ps.setInt(2, characteristic.getId());
						ps.setInt(3, characteristic.getValue());
						ps.executeUpdate();
					}
				}
				connection.commit();
			}
			catch(SQLException e) {
				//Rollback 
				connection.rollback();
				throw e;
			}
			finally {
				connection.setAutoCommit(true);
			}
		}
	

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