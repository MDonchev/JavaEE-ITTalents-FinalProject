package com.nargilemag.model.dao;

import java.lang.reflect.Array;
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
	
	public Product getProductBtID(int id) throws SQLException {

		String sql = "SELECT id, name, description, price, ammount_in_stock, category_id, img_url, discount_percent FROM products WHERE id = ?";
		Product p = null;
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				p = new Product(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getInt("ammount_in_stock"),
						rs.getInt("category_id"),
						CharacteristicDao.INSTANCE.getCharacteristicsByProductId(id),
						rs.getString("img_url"),
						rs.getInt("discount_percent"));
			}
			
		}
		return p;
	}
	
	public void updateProductAmmountInStock(int id, int newAmmount) throws SQLException {
		String sql = "UPDATE products SET ammount_in_stock = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, newAmmount);
		ps.setInt(2, id);
		
		ps.executeUpdate();
	}
	
	public void updateDiscountPercentByProductId(int id, int newDiscountPercent) throws SQLException {
		String sql = "UPDATE products SET discount_percent = ? WHERE id = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, newDiscountPercent);
			ps.setInt(2, id);
			
			ps.executeUpdate();
		}
		
	}
	
	public int getProductCharacteristicById(int id) throws SQLException {
		String sql = "SELECT value FROM products_have_characteristics WHERE products_id = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery().getInt(1);
	}
	
	public List<Product> getAllProductsByCategoryId(int id) throws SQLException {
		
		String sql = "SELECT p.id, p.name, description, price, ammount_in_stock, category_id, img_url, discount_percent FROM products p"
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
						CharacteristicDao.INSTANCE.getCharacteristicsByProductId(id),
						rs.getString("img_url"),
						rs.getInt("discount_percent")));
			}
			
		}
		
		return products;
	}
	
	public List<Product> getAllProducts() throws SQLException {
		
		String sql = "SELECT id, name, description, price, ammount_in_stock, category_id, img_url, discount_percent FROM products";
		
		ArrayList<Product> products = new ArrayList();
		
		Statement s = connection.createStatement();
		ResultSet result = s.executeQuery(sql);
			
		while(result.next()) {
				products.add(new Product(
						result.getInt("id"),
						result.getString("name"),
						result.getString("description"),
						result.getDouble("price"),
						result.getInt("ammount_in_stock"),
						result.getInt("category_id"),
						CharacteristicDao.INSTANCE.getCharacteristicsByProductId(result.getInt("id")),
						result.getString("img_url"),
						result.getInt("discount_percent")));
		}
		return products;
	}
	
	public void saveProduct(Product prod) throws SQLException {
		synchronized (connection) {
			
			connection.setAutoCommit(false);
			try {
				ResultSet result = null;
				//Insert in products table
				try(PreparedStatement ps = connection.prepareStatement("INSERT INTO products (name, description, price, ammount_in_stock, category_id, img_url) VALUES (?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS)){
					ps.setString(1, prod.getName());
					ps.setString(2, prod.getDescription());
					ps.setDouble(3, prod.getPrice());
					ps.setInt(4, prod.getAmmountInStock());
					ps.setInt(5, prod.getCategoryId());
					ps.setString(6, prod.getImgURL());
					
					ps.executeUpdate();
					
					result = ps.getGeneratedKeys();
					result.next();
					int productId = result.getInt(1);
					
					prod.setId(productId);
				}
				
				//Insert in products_characteristics
				List<Characteristic> characteristics = prod.getCharacteristics();
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
	
	public ArrayList<Product> getProductsWithNameLike(String str) throws SQLException{
		String sql = "SELECT id, name, description, price, ammount_in_stock, category_id, img_url, discount_percent "
					+ "FROM products "
					+ "WHERE name LIKE \"%" + str + "%\" "
					+ "LIMIT 10";	
		
		
		ArrayList<Product> result = new ArrayList<>();
		
		try(Statement st = connection.createStatement()){
			
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				result.add(new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getString("description"),
							rs.getDouble("price"),
							rs.getInt("ammount_in_stock"),
							rs.getInt("category_id"),
							CharacteristicDao.INSTANCE.getCharacteristicsByProductId(rs.getInt("id")),
							rs.getString("img_url"),
							rs.getInt("discount_percent")
						));
			}
		}
		
		return result;
	}
	
	public List<String> getAllEmailsOfUsersWithFavoriteProductId(int id) throws SQLException{
		String sql = "SELECT email FROM users JOIN favorite_products f ON id = f.users_id WHERE f.products_id = ?";
		ArrayList<String> emails = new ArrayList<>();
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				emails.add(rs.getString("email"));
			}
		}
		
		return emails;
	}
	
	public List<Product> getUserFavourites(User u) throws SQLException {

		String sql = "SELECT p.id, name, description, price, ammount_in_stock, category_id, img_url, discount_percent FROM favorite_products AS fp JOIN products AS p ON fp.product_id = p.id WHERE fp.users_id = ?";
		
		ArrayList<Product> products = new ArrayList();
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, u.getId());
			ResultSet result = ps.executeQuery();
				
			while(result.next()) {
					products.add(new Product(
							result.getInt("id"),
							result.getString("name"),
							result.getString("description"),
							result.getDouble("price"),
							result.getInt("ammount_in_stock"),
							result.getInt("category_id"),
							CharacteristicDao.INSTANCE.getCharacteristicsByProductId(result.getInt("id")),
							result.getString("img_url"),
							result.getInt("discount_percent")));
			}
		}
		return products;
	}

}