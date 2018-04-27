package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.manager.DBManager;
import model.Characteristic;

public class CharacteristicDao {

	private static CharacteristicDao instance;
	private static Connection connection;
	
	private CharacteristicDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static CharacteristicDao getInstance() {
		if(instance == null) {
			instance = new CharacteristicDao();
		}
		return instance;
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
