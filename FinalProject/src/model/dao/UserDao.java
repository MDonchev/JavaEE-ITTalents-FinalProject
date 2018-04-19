package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.manager.DBManager;
import model.Gender;
import model.User;
import util.BCrypt;
import util.exceptions.UserDataException;


public class UserDao implements IUserDao{

	private static UserDao instance;
	private static Connection con;
	
	private UserDao() {
		con = DBManager.getInstance().getConnection();
	}
	
	public synchronized static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public synchronized void saveUser(User u) throws SQLException {
		String sql = "INSERT INTO users (username,password,email,address,phone_number,is_admin,gender_id) VALUES (?,?,?,?,?,?,?);";
		
		try(PreparedStatement ps = con.prepareStatement(sql);){
			
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
		
		PreparedStatement ps = con.prepareStatement(sql);
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
		Statement s = con.createStatement();
		ResultSet result = s.executeQuery(sql);
		List<Gender> genders = new ArrayList<>();
		while(result.next()) {
			genders.add(new Gender(result.getInt("id"), result.getString("type")));
		}
		return genders;
	}

}