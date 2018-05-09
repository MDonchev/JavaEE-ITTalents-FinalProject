package com.nargilemag.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.nargilemag.model.Order;
import com.nargilemag.model.Product;
import com.nargilemag.model.User;


public enum OrderDao{

	INSTANCE;
	
	private Connection connection;
	
	private OrderDao() {
		connection = DBManager.INSTANCE.getConnection();
	}
	
	public synchronized void addOrderFromUser(Order order, User user) throws SQLException {
		String sql = "INSERT INTO orders (date_of_issue, address, phone_number, users_id) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			ps.setDate(1, Date.valueOf(order.getDateOfIssue()));
			ps.setString(2, order.getAddress());
			ps.setString(3, order.getPhoneNumber());
			ps.setInt(4, user.getId());
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 0) {
				throw new SQLException("Inserting order failed, no rows in orders affected");
			}
			
			try(ResultSet gKeys = ps.getGeneratedKeys()) {
				if(gKeys.next()) {
					order.setId(gKeys.getInt(1));
				}
				else {
					throw new SQLException("Failed to set the id of the order which is to be inserted");
				}
			}
			
			
			ps.executeUpdate();
		}
		
		
	}
	
	public void addProductToOrder(Order order, Product product) throws SQLException {
		String sql = "INSERT INTO orders_has_products (orders_id, products_id) VALUES (?, ?)";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, order.getId());
			ps.setInt(2, product.getId());
			
			ps.executeUpdate();
		}
	}
	
}
