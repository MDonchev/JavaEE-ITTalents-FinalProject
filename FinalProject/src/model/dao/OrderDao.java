package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.manager.DBManager;
import model.Order;
import model.User;

public class OrderDao implements IOrderDao{

	private static OrderDao instance;
	private static Connection connection;
	
	private OrderDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public static OrderDao getInstance() {
		if(instance == null) {
			instance = new OrderDao();
		}
		return instance;
	}
	
	public synchronized void addOrderFromUser(Order order, User user) throws SQLException {
		String sql = "INSERT INTO orders (date_of_issue, address, phone_number, users_id) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setDate(1, Date.valueOf(order.getDateOfIssue()));
			ps.setString(2, order.getAddress());
			ps.setString(3, order.getPhoneNumber());
			ps.setInt(4, user.getId());
			
			ps.execute();
		}
		
	}
	
	
	
}
