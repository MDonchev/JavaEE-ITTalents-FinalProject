package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.nargilemag.model.Gender;
import com.nargilemag.model.User;
import com.nargilemag.util.BCrypt;
import com.nargilemag.util.exceptions.UserDataException;




public enum UserDao implements IUserDao{
	
	INSTANCE;
	
	private Connection connection;
	

	private UserDao() {
		connection = DBManager.INSTANCE.getConnection();
	}
	
	public synchronized void saveUser(User u) throws SQLException {
		String sql = "INSERT INTO users (username,password,email,address,phone_number,is_admin,gender_id) VALUES (?,?,?,?,?,?,?);";
		
		try(PreparedStatement ps = connection.prepareStatement(sql);){
			
			String hashedPass = u.hashPassword();
			
			ps.setString(1, u.getUsername());
			ps.setString(2, hashedPass);
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getAddress());
			ps.setString(5, u.getPhoneNumber());
			ps.setBoolean(6, u.isAdmin());
			ps.setInt(7, u.getGender());
			
			ps.executeUpdate();
		}
	}
	
	// TODO : make it better
	public User getUserFromLogin(String username, String pass) throws SQLException, UserDataException {
		String sql = "SELECT id, username, password, email, address, phone_number, is_admin, gender_id FROM users WHERE username = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet result = ps.executeQuery();
		
		// TODO
		List<Integer> favourites = new ArrayList<>();

		
		if(result.next() && BCrypt.checkpw(pass, result.getString("password"))) {
			return new User(result.getString("username"),
							result.getString("password"),
							result.getString("email"),
							result.getString("address"),
							result.getString("phone_number"),
							result.getInt("gender_id"),
							result.getBoolean("is_admin")
							);
		}
		else {
			return null;
		}
	}
	
	
	

	public List<Gender> getAllGenders() throws SQLException {
		String sql = "SELECT id, type FROM genders;";
		Statement s = connection.createStatement();
		ResultSet result = s.executeQuery(sql);
		List<Gender> genders = new ArrayList<>();
		while(result.next()) {
			genders.add(new Gender(result.getInt("id"), result.getString("type")));
		}
		return genders;
	}

}