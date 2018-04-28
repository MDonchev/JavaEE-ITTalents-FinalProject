package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nargilemag.model.Characteristic;


public enum CharacteristicDao {

	INSTANCE;
	
	private Connection connection;
	
	private CharacteristicDao() {
		connection = DBManager.INSTANCE.getConnection();
	}
	
	
	public List<Characteristic> getCharacteristicsByProductId(int productId) throws SQLException {
		ArrayList<Characteristic> characteristics = new ArrayList();
		
		String sql = "SELECT name unit value FROM products_have_characteristics p"
				+ "JOIN characteristics c ON p.characteristics_id = c.id"
				+ "WHERE p.products_id = ?";
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				characteristics.add(
						new Characteristic(
								rs.getString("name"),
								rs.getString("unit"),
								rs.getString("value")));
			}
			
		}
		
		return characteristics;
		
		
	}
	
}
