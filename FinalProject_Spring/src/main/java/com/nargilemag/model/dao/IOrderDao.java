package com.nargilemag.model.dao;

import java.sql.SQLException;

import com.nargilemag.model.Order;
import com.nargilemag.model.User;

public interface IOrderDao {

	public void addOrderFromUser(Order order, User user) throws SQLException;
	
}
