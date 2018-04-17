package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.manager.DBManager;
import model.User;
import util.BCrypt;


public class UserDao {

	private static UserDao instance;
	private static Connection con;
	
	private UserDao() {
		con = DBManager.getInstance().getConnection();
	}
	
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public void saveUser(User u) throws SQLException {
		String sql = "INSERT INTO users (username,password,email,address,phone_number,is_admin,sex) VALUES (?,?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(sql);
		
		String hashedPass = u.hashPassword();
		
		ps.setString(1, u.getUsername());
		ps.setString(2, hashedPass);
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getAddress());
		ps.setString(5, u.getPhoneNumber());
		ps.setBoolean(6, u.isAdmin());
		ps.setString(7, u.getEmail());
		
		ps.executeUpdate();
	}
/*
	public User getUser(String username, String pass) throws SQLException {
		String sql = "SELECT id, username, password, email, gender_id FROM users WHERE username = ? AND password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, pass);
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			return new User();
		}
		else {
			return null;
		}
	}
	*/
}