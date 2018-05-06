package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nargilemag.model.Category;

public enum CategoryDao {

	INSTANCE;
	
	private Connection connection;
	
	private CategoryDao() {
		connection = DBManager.INSTANCE.getConnection();
	}
	
	
	public List<Category> getSubCategories() throws SQLException {
		String sql = "SELECT id, name, categories_id FROM categories where categories_id is not null;";
		Statement s = connection.createStatement();
		ResultSet result = s.executeQuery(sql);
		List<Category> cats = new ArrayList<>();
		while(result.next()) {
			cats.add(new Category(result.getInt("id"), result.getString("name"), result.getInt("categories_id")));
		}
		return cats;
	}
	
	public List<Category> getCategories() throws SQLException {
		String sql = "SELECT id, name FROM categories where categories_id is null;";
		Statement s = connection.createStatement();
		ResultSet result = s.executeQuery(sql);
		List<Category> cats = new ArrayList<>();
		while(result.next()) {
			cats.add(new Category(result.getInt("id"), result.getString("name"), null));
		}
		return cats;
	}
	
	public String getCategoryNameById(int id) throws SQLException {
		String sql = "SELECT name FROM categories where id = ?;";
		
		 try(PreparedStatement ps = connection.prepareStatement(sql)){
			 ps.setInt(1, id);
			 
			 ResultSet rs = ps.executeQuery();
			 rs.next();
			 return rs.getString("name");
		 }
	}
	
}
