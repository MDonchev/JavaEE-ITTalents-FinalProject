package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nargilemag.model.Order;
import com.nargilemag.model.User;


public enum OrderDao implements IOrderDao{

	INSTANCE;
	
	private Connection connection;
	
	private OrderDao() {
		connection = DBManager.INSTANCE.getConnection();
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
