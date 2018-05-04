package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nargilemag.model.Characteristic;
import com.nargilemag.model.Gender;


public enum CharacteristicDao {

	INSTANCE;
	
	private Connection connection;
	
	private CharacteristicDao() {
		connection = DBManager.INSTANCE.getConnection();
	}
	
	
	public List<Characteristic> getCharacteristicsByProductId(int productId) throws SQLException {
		ArrayList<Characteristic> characteristics = new ArrayList();
		
		String sql = "SELECT name, unit, value, categories_id FROM products_have_characteristics p"
				+ " JOIN characteristics c ON p.characteristics_id = c.id"
				+ " WHERE p.products_id = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				characteristics.add(
						new Characteristic(
								rs.getString("name"),
								rs.getString("unit"),
								rs.getInt("value"),
								rs.getInt("categories_id")));
			}
			
		}
		
		return characteristics;
	}
	public List<Characteristic> getAllCharacteristics() throws SQLException{
		String sql = "SELECT id, name, categories_id FROM characteristics;";
		Statement s = connection.createStatement();
		ResultSet result = s.executeQuery(sql);
		List<Characteristic> character = new ArrayList<>();
		while(result.next()) {
			character.add(new Characteristic(result.getInt("id"),result.getString("name"),null,0,result.getInt("categories_id")));
		}
		
		return character;
	}
	
	public String getCharacteristicNameByID(int id) throws SQLException {
		
		String sql = "SELECT name FROM characteristics WHERE id = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, id);
			
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return result.getString("name");
			}
			return null;
		}
	}


	public Integer getCharacteristicCategory(int id) throws SQLException {
		String sql = "SELECT categories_id FROM characteristics WHERE id = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, id);
			
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return result.getInt("categories_id");
			}
			return null;
		}
	}
}
