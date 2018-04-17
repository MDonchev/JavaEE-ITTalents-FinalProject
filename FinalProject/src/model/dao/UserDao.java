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
		String sql = "INSERT INTO users (username, password, email, gender_id) VALUES (?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getEmail());
		ps.setInt(4, u.getGenderId());
		ps.executeUpdate();
	}

	public User getUser(String username, String pass) throws SQLException {
		String sql = "SELECT id, username, password, email, gender_id FROM users WHERE username = ? AND password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, pass);
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			return new User(result.getInt("id"),
					result.getString("username"),
					result.getString("password"),
					result.getString("email"),
					result.getInt("gender_id"));
		}
		else {
			return null;
		}
	}
}